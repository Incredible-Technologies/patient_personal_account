package com.personal.patient.account.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/unsecured")
    public String unseuredData(){
        return "Unsecursed data";
    }

    @GetMapping("/secured")
    public String seuredData(){
        return "Secursed data";
    }

    @GetMapping("/admin")
    public String adminData(){
        return "Admin data";
    }

    @GetMapping("/info")
    public String userData(Principal principal){

        return principal.getName();
    }

}
