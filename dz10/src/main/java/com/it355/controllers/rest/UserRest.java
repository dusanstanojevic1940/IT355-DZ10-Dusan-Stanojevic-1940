package com.it355.controllers.rest;

import com.it355.dao.UserDao;
import com.it355.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserRest {

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public List getUsers() {
        return userDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {

        return new ResponseEntity(userDao.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity createUser(@RequestBody User user) {

        return new ResponseEntity(userDao.insert(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userDao.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return new ResponseEntity(userDao.update(user), HttpStatus.OK);
    }

}
