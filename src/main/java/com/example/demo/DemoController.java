package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DemoController {
    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Algorithms algorithms;

    @RequestMapping("/")
    public String Index() {
        logger.trace("Home method accessed");
        return "Spring Demo";
    }

    // Create
    @PostMapping("/add")
    public String addCustomer(@RequestParam String first, @RequestParam String last) {
        logger.trace("Add method accessed");
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerRepository.save(customer);
        logger.trace("Successfully added " + customer.getFirstName() + " " + customer.getLastName() + " to database");

        return "Successfully added new customer!";
    }
    // Read all
    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        logger.trace("get-all method accessed");
        return customerRepository.findAll();
    }
    // Read by ID
    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        logger.trace("get-by-id method accessed");

        if (!customerRepository.existsById(id)) {
            logger.error("Could not find customer by id");
            return null;
        }

        logger.trace("Successfully found customer "
                + customerRepository.findCustomerById(id).getFirstName() + " "
                + customerRepository.findCustomerById(id).getLastName());
        return customerRepository.findCustomerById(id);
    }
    // Update
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Integer id,
                                 @RequestParam String updateFirst,
                                 @RequestParam String updateLast) {

        logger.trace("update-by-id method accessed");
        if (!customerRepository.existsById(id)) {
            logger.error("Could not find customer by id");
            return "Could not find customer";
        }

        Customer customer = customerRepository.findCustomerById(id);
        logger.trace("Found customer "
                + customer.getFirstName() + " " + customer.getLastName());

        customer.setFirstName(updateFirst);
        customer.setLastName(updateLast);
        customerRepository.save(customer);

        logger.trace("Updated customer to "
                + customer.getFirstName() + " " + customer.getLastName());

        return "Updated customer name!";
    }
    // Delete
    @GetMapping("/delete")
    public String deleteCustomers() {
        logger.trace("delete-all method accessed");

        customerRepository.deleteAll();

        return "Deleted all entries";
    }
    // Delete by ID
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        logger.trace("Delete-by-id method accessed");
        if (!customerRepository.existsById(id)) {
            logger.error("Customer not found");
            return "Customer doesn't exist from database!";

        }

        logger.trace("Found customer "
                + customerRepository.findCustomerById(id).getFirstName() + " "
                + customerRepository.findCustomerById(id).getLastName());
        customerRepository.deleteById(id);
        logger.trace("Deleted customer from database");

        return "Deleted customer from database.";
    }

    @GetMapping("/fib/{num}")
    public ArrayList<Integer> getFibonacci(@PathVariable Integer num) {
        logger.trace("Fibonacci method accessed");
        return algorithms.dynamicFib(num);
    }

    @GetMapping("/prime/{num}")
    public boolean isNumPrime(@PathVariable Integer num) {
        logger.trace("Prime method accessed");
        return algorithms.isPrime(num);
    }
}
