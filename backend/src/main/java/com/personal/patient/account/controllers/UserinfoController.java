package com.personal.patient.account.controllers;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Userinfo;
import com.personal.patient.account.models.UserInfoRepresentation;
import com.personal.patient.account.models.FullUserInfoRepresentation;
import com.personal.patient.account.service.UserSrevice;
import com.personal.patient.account.service.UserinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userinfo")
public class UserinfoController {
    private final UserinfoService userinfoService;
    private final UserSrevice userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveOrChangeUserinfo(@RequestBody UserInfoRepresentation userInfoRequest, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        userinfoService.createOrChangeUserinfo(userInfoRequest, user);
        return ResponseEntity.ok(new FullUserInfoRepresentation(userInfoRequest, user.getId()));
    }

    @GetMapping("")
    public ResponseEntity<?> getUserinfo(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Userinfo userinfo = userinfoService.getUserinfoByUser(user);
        return ResponseEntity.ok(new UserInfoRepresentation(userinfo));
    }
}
