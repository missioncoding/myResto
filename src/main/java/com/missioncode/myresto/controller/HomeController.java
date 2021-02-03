package com.missioncode.myresto.controller;

import com.missioncode.myresto.form.SearchData;
import com.missioncode.myresto.model.Customer;
import com.missioncode.myresto.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/")
    public String getHome(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers",customers);
        model.addAttribute("search", new SearchData());
        return "index";
    }
}
