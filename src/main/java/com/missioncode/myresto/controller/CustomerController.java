package com.missioncode.myresto.controller;

import com.missioncode.myresto.form.SearchData;
import com.missioncode.myresto.model.Customer;
import com.missioncode.myresto.repository.CustomerRepository;
import com.missioncode.myresto.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/myresto")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/customers")
    public String getCustomers(Model model) {
        System.out.println("Called getCustomers....");
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers",customers);
        return "customers";
    }

    @RequestMapping(value = "/customer",method = RequestMethod.GET)
    public String createCustomer(Model model) {
        System.out.println("Called createCustomers ....GET");
        model.addAttribute("customer", new Customer());
        return "createcustomer";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String insert(Customer customer) {
        System.out.println("Called insert ....POST");
        customerService.insert(customer);
        return "redirect:/myresto/customers";
    }

    @RequestMapping(value = "/updatecustomer/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id,Model model) {
        System.out.println("Called Update .... GET");
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "createcustomer";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(SearchData searchData, Model model) {
        if (searchData.getValue().isEmpty()) {
            return "redirect:/myresto/customers";
        } else {
            if (searchData.getType().equals("name")) {
                return "redirect:/myresto/name/"+ searchData.getValue();
            } else if (searchData.getType().equals("email")) {
                return "redirect:/myresto/email/"+ searchData.getValue();
            } else if (searchData.getType().equals("phone")) {
                return "redirect:/myresto/phone/"+ searchData.getValue();
            } else {
                return "redirect:/myresto/customers";
            }
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        customerService.delete(id);
        return "redirect:/myresto/customers";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") String id) {
        Customer customer = customerService.findById(id);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        return "customers";
    }

    @RequestMapping(value = "/{type}/{value}", method = RequestMethod.GET)
    public String getByCustomerName(@PathVariable("type") String type ,@PathVariable("value") String value,Model model) {
        List<Customer> customers = null;
        if (type.equals("name")) {
            customers = customerService.findByName(value);
        } else if (type.equals("phone")) {
            customers = customerService.findByPhone(value);
        } else if (type.equals("email")) {
            customers = customerService.findByEmail(value);
        } else {
            return "redirect:/myresto/customers";
        }
        model.addAttribute("customers",customers);
        return "customers";
    }

}
