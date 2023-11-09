package com.personal.patient.account.controllers;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Profile;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.ProfileRepresentation;
import com.personal.patient.account.models.FullProfileRepresentation;
import com.personal.patient.account.service.UserService;
import com.personal.patient.account.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @PostMapping("")
    public ResponseEntity<?> saveOrChangeProfile(@RequestBody ProfileRepresentation profileRequest, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        profileService.createOrChangeProfile(profileRequest, user);
        return ResponseEntity.ok(new FullProfileRepresentation(profileRequest, user.getId()));
    }

    @GetMapping("")
    public ResponseEntity<?> getProfile(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Profile profile = profileService.getProfileByUser(user);
        ProfileRepresentation profileRepresentation = new ProfileRepresentation(profile);
        return ResponseEntity.ok(profileRepresentation);
    }
}
