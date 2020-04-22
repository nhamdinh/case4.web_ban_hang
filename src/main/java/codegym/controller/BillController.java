package codegym.controller;

import codegym.model.Bill;
import codegym.service.BillService;
import codegym.service.GiohangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private GiohangService giohangService;


    @GetMapping("/list-bills")
    public String showBills(Model model) {
        model.addAttribute("bills", billService.findAll());
        return "bill/listBill";
    }
    @GetMapping("/show-thanhtoan")
    public String giohangToBill(Model model) {
        model.addAttribute("giohangs", giohangService.findAll());
        return "bill/listBill";
    }


}
