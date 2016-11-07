package com.it355.controllers.rest;
import java.util.ArrayList;
import java.util.List;

import com.it355.dao.ContactDao;
import com.it355.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/contacts")
public class ContactsRest {

    @Autowired
    private ContactDao contactDao;

    @GetMapping("/")
    public List getContacts() {
        return contactDao.findAllForUser(1);
    }

    @GetMapping("/{id}")
    public ResponseEntity getContact(@PathVariable("id") Long id) {

        return new ResponseEntity(contactDao.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity createContact(@RequestBody Contact customer) {

        return new ResponseEntity(contactDao.insert(customer, 1), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContact(@PathVariable Long id) {
        contactDao.delete(id, 1);
        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateContact(@PathVariable Long id, @RequestBody Contact contact) {

        return new ResponseEntity(contactDao.update(contact), HttpStatus.OK);
    }

}