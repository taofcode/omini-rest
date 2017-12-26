package zw.co.sbs.steward.freelancer.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zw.co.sbs.steward.freelancer.domain.Accounts;
import zw.co.sbs.steward.freelancer.service.AccountService;


import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    final static Logger logger = Logger.getLogger(AccountsController.class);
    @Autowired
    AccountService accountService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Accounts> addAccounts(@RequestBody Accounts Accounts) {
        accountService.save(Accounts);
        logger.debug("Added:: " + Accounts);
        return new ResponseEntity<Accounts>(Accounts, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAccounts(@RequestBody Accounts Accounts) {
        Accounts existingEmp = accountService.getById(Accounts.getId());
        if (existingEmp == null) {
            logger.debug("Accounts with id " + Accounts.getId() + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            accountService.save(Accounts);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Accounts> getAccounts(@PathVariable("id") String accountNumber) {
        Accounts Accounts = accountService.findOne(accountNumber);
        if (Accounts == null) {
            logger.debug("Accounts with id " + accountNumber + " does not exists");
            return new ResponseEntity<Accounts>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found Accounts:: " + Accounts);
        return new ResponseEntity<Accounts>(Accounts, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Accounts>> getAllAccounts() {
        List<Accounts> Accounts = accountService.getAll();
        if (Accounts.isEmpty()) {
            logger.debug("Accounts do not exist");
            return new ResponseEntity<List<Accounts>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found " + Accounts.size() + " Accounts");
        logger.debug(Arrays.toString(Accounts.toArray()));
        return new ResponseEntity<List<Accounts>>(Accounts, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccounts(@PathVariable("id") Long id) {
        Accounts Accounts = accountService.getById(id);
        if (Accounts == null) {
            logger.debug("Accounts with id " + id + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            accountService.delete(id);
            logger.debug("Accounts with id " + id + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }
}
