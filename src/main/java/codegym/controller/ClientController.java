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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private HaisanService haisanService;
    @Autowired
    private PhanloaiService phanloaiService;

    @ModelAttribute("phanloais")
    public Iterable<Phanloai> Phanloais() {
        return phanloaiService.findAll();
    }

    @GetMapping("/homehaisan")
    public String homehaisan() {
        return "redirect:/haisan";
    }

    @GetMapping("/haisan")
    public String index(Model model, HttpServletRequest request
            , RedirectAttributes redirect) {
        request.getSession().setAttribute("haisanlist", null);

        if (model.asMap().get("success") != null)
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        return "redirect:/haisan/page=1";
    }

    @GetMapping("/haisan/page={pageNumber}")
    public String showhaisanPage(HttpServletRequest request,
                                 @PathVariable int pageNumber, Model model) {
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("haisanlist");
        int pagesize = 3;
        List<Haisan> list = (List<Haisan>) haisanService.findAll();
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
        request.getSession().setAttribute("haisanlist", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/haisan/page=";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);

        model.addAttribute("haisans", pages);

        return "client/listC";
    }

   @GetMapping("/haisan/search/{pageNumber}")
    public String search(@RequestParam("s") String s,
                         Model model,
                         HttpServletRequest request,
                         @PathVariable int pageNumber) {
        if (s.equals("")) {
            return "redirect:/haisan";
        }
        List<Haisan> list = (List<Haisan>) haisanService.search(s);
        if (list == null) {
            return "redirect:/homehaisan";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("employeelist");
        int pagesize = 3;
        pages = new PagedListHolder<>(list);
        pages.setPageSize(pagesize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("haisanlist", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/haisan/page=";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("haisans", pages);
        return "client/listC";
    }


    @GetMapping("/haisan/{id}/view")
    public ModelAndView showEditForm(@PathVariable int id) {
        Haisan haisan = haisanService.findOne(id);
        if (haisan != null) {
            ModelAndView modelAndView = new ModelAndView("/client/viewC");
            modelAndView.addObject("haisan", haisan);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }



}
