package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DemoController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Algorithms algorithms;

    // Create
    @PostMapping("/add")
    public String addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerRepository.save(customer);

        return "Added new customer to repo!";
    }

    // Update
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Integer id,
                                 @RequestParam String updateFirst,
                                 @RequestParam String updateLast) {

        Customer customer = customerRepository.findCustomerById(id);
        customer.setFirstName(updateFirst);
        customer.setLastName(updateLast);
        customerRepository.save(customer);

        return "Updated customer name!";
    }
    // Read
    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        return customer;
    }

    // Delete
    @GetMapping("/delete")
    public String deleteCustomers() {
        customerRepository.deleteAll();
        return "Deleted all entries";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
        return "Deleted customer from database.";
    }


    @GetMapping("/fib/{num}")
    public ArrayList<Integer> getFibonacci(@PathVariable Integer num) { return algorithms.dynamicFib(num); }

    @GetMapping("/prime/{num}")
    public boolean isNumPrime(@PathVariable Integer num) { return algorithms.isPrime(num); }
}
