package com.personal.patient.account.controllers;

import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.ListOfResultCard;
import com.personal.patient.account.service.ResultCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final ResultCardService resultCardService;

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping("/all-cards")
    public ResponseEntity<?> getAllUserResultCard(Principal principal){
        ListOfResultCard result = new ListOfResultCard(resultCardService.findAllByUser(principal));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all-not-done-cards")
    public ResponseEntity<?> getAllUserNotDoneResultCard(Principal principal){
        ListOfResultCard result = new ListOfResultCard(resultCardService.findAllNotDoneByUser(principal));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all-done-cards")
    public ResponseEntity<?> getAllUserDoneResultCard(Principal principal){
        ListOfResultCard doneResult = new ListOfResultCard(resultCardService.findAllDoneByUser(principal));
        return ResponseEntity.ok(doneResult);
    }
}
