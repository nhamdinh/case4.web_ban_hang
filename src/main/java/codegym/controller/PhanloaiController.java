package codegym.controller;


import codegym.model.Haisan;
import codegym.model.Phanloai;
import codegym.service.HaisanService;
import codegym.service.PhanloaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PhanloaiController {
    @Autowired
    private PhanloaiService phanloaiService;
    @Autowired
    private HaisanService haisanService;

    @GetMapping("/admin/homephanloai")
    public String homephanloai() {
        return "redirect:/admin/phanloai";
    }

    @GetMapping("/admin/phanloai")
    public String index(Model model, HttpServletRequest request
            , RedirectAttributes redirect) {
        request.getSession().setAttribute("phanloailist", null);

        if (model.asMap().get("success") != null)
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        return "redirect:/admin/phanloai/page=1";
    }

    @GetMapping("/admin/phanloai/page={pageNumber}")
    public String showPhanloaiPage(HttpServletRequest request,
                                   @PathVariable int pageNumber, Model model) {
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("phanloailist");
        int pagesize = 3;
        List<Phanloai> list = (List<Phanloai>) phanloaiService.findAll();
        System.out.println(list.size());
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("phanloailist", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/admin/phanloai/page=";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);

        model.addAttribute("phanloais", pages);

        return "phanloai/listP";
    }

    @GetMapping("/admin/create-phanloai")
    public ModelAndView showCreateFormPhanloai() {
        ModelAndView modelAndView = new ModelAndView("phanloai/formP");
        modelAndView.addObject("phanloai", new Phanloai());
        return modelAndView;
    }

    @PostMapping("/admin/save-phanloai")
    public String savePhanloai(@Valid Phanloai phanloai, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "phanloai/formP";
        }
        phanloaiService.save(phanloai);
        redirect.addFlashAttribute("success", "Saved employee successfully!");
        return "redirect:/admin/create-phanloai";
    }


    @GetMapping("/admin/phanloai/{id}/edit")
    public String showEditFormPhanloai(@PathVariable int id, Model model) {

        model.addAttribute("phanloai", phanloaiService.findOne(id));
        return "phanloai/formP";
    }
    @GetMapping("/admin/phanloai/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        Phanloai emp = phanloaiService.findOne(id);
        phanloaiService.delete(emp);
        redirect.addFlashAttribute("success", "Deleted employee successfully!");
        return "redirect:/admin/homephanloai";
    }

    @GetMapping("/admin/phanloai/search/{pageNumber}")
    public String search(@RequestParam("s") String s,
                         Model model,
                         HttpServletRequest request,
                         @PathVariable int pageNumber) {
        if (s.equals("")) {
            return "redirect:/admin/phanloai";
        }
        List<Phanloai> list = (List<Phanloai>) phanloaiService.search(s);
        if (list == null) {
            return "redirect:/admin/homephanloai";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("employeelist");
        int pagesize = 3;
        pages = new PagedListHolder<>(list);
        pages.setPageSize(pagesize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("phanloailist", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/admin/phanloai/page=";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("phanloais", pages);
        return "phanloai/listP";
    }


    @GetMapping("/admin/view-phanloai/{id}")
//    @RequestMapping(value = { "/view-phanloai/{id}"}, method = RequestMethod.GET)
//  @RequestMapping(value = {"view-phanloai/{id}","view/admin/phanloai/{id}"}, method = RequestMethod.GET )
    public ModelAndView viewPhanloai(@PathVariable("id") int id){
        Phanloai phanloai = phanloaiService.findOne(id);
        if(phanloai == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Haisan> haisans = haisanService.findAllByPhanloai(phanloai);

        ModelAndView modelAndView = new ModelAndView("phanloai/viewP");
        modelAndView.addObject("phanloai", phanloai);
        modelAndView.addObject("haisans", haisans);
        return modelAndView;
    }

}
