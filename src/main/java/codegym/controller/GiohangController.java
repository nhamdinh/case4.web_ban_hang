package codegym.controller;

import codegym.model.Giohang;
import codegym.model.Haisan;
import codegym.service.GiohangService;
import codegym.service.HaisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GiohangController {
    @Autowired
    private HaisanService haisanService;
    @Autowired
    private GiohangService giohangService;


    @PostMapping("/buy-cart")
    public String buyHaisan(@ModelAttribute("haisan") Giohang haisan) {
        giohangService.save(haisan);
        return "redirect:/homehaisan";
    }

    @GetMapping("/haisan/{id}/buy")
    public ModelAndView chooseHaisan(@PathVariable int id) {
        Haisan haisan = haisanService.findOne(id);
        if (haisan != null) {
            ModelAndView modelAndView = new ModelAndView("/client/viewCG");
            modelAndView.addObject("haisan", haisan);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }



}
