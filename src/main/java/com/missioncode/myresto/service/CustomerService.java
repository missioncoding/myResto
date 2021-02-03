package com.missioncode.myresto.service;

import com.missioncode.myresto.model.Customer;
import com.missioncode.myresto.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        List<Customer> customerList =  customerRepository.findAll();
        return customerList;
    }

    public void insert(Customer customer) {
        customerRepository.save(customer);
    }

    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    public Customer findById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.get();
    }

    public List<Customer> findByName(String name) {
        List<Customer> customers = customerRepository.findByFullname(name);
        return customers;
    }

    public List<Customer> findByEmail(String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        return customers;
    }

    public List<Customer> findByPhone(String phone) {
        List<Customer> customers = customerRepository.findByPhone(phone);
        return customers;
    }

 }

