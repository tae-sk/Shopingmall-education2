package com.taeseok.apis.route;

import org.springframework.web.bind.annotation.*;

@RestController
public class User {
    @GetMapping("/user")
    public String getUsers(){
        return "users!";
    }
}
