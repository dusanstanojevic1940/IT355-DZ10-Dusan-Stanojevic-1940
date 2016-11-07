package com.it355.services;

import com.it355.models.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public String validateUser(User user) {
        if (user.getUsername().equals("Dusan") && user.getPassword().equals("DobarPass")) {
            return "true";
        } else {
            return "false";
        }
    }
}
