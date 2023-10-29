package com.personal.patient.account.controllers;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.models.SaveAuthedUserInfoRequest;
import com.personal.patient.account.service.UserSrevice;
import com.personal.patient.account.service.UserinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserinfoController {
    private final UserinfoService userinfoService;

    private final UserSrevice userService;

    @PostMapping("/save/userinfo")
    public ResponseEntity<?> createAuthToken(@RequestBody SaveAuthedUserInfoRequest userInfoRequest, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        return userinfoService.createNewUserinfo(userInfoRequest, user);
    }
}
