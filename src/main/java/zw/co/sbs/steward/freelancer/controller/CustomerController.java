package zw.co.sbs.steward.freelancer.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.sbs.steward.freelancer.domain.Customers;
import zw.co.sbs.steward.freelancer.service.CustomerService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    final static Logger logger = Logger.getLogger(CustomerController.class);
    @Autowired
    CustomerService customerService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customers> addCustomers(@RequestBody Customers Customers) {
        customerService.save(Customers);
        logger.debug("Added:: " + Customers);
        return new ResponseEntity<Customers>(Customers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCustomers(@RequestBody Customers Customers) {
        Customers existingEmp = customerService.getById(Customers.getId());
        if (existingEmp == null) {
            logger.debug("Customers with id " + Customers.getId() + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            customerService.save(Customers);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customers> getCustomers(@PathVariable("id") String accountNumber) {
        Customers Customers = customerService.findOne(accountNumber);
        if (Customers == null) {
            logger.debug("Customers with id " + accountNumber + " does not exists");
            return new ResponseEntity<Customers>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found Customers:: " + Customers);
        return new ResponseEntity<Customers>(Customers, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> Customers = customerService.getAll();
        if (Customers.isEmpty()) {
            logger.debug("Customers do not exist");
            return new ResponseEntity<List<Customers>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found " + Customers.size() + " Customers");
        logger.debug(Arrays.toString(Customers.toArray()));
        return new ResponseEntity<List<Customers>>(Customers, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCustomers(@PathVariable("id") Long id) {
        Customers Customers = customerService.getById(id);
        if (Customers == null) {
            logger.debug("Customers with id " + id + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            customerService.delete(id);
            logger.debug("Customers with id " + id + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }
}
