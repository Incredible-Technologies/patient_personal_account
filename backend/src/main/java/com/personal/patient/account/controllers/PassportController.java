package com.personal.patient.account.controllers;

import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingPassportResponse;
import com.personal.patient.account.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class PassportController {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка валидации: поле user_id уже существует");
    }
    private final PassportService passportService;

    @PostMapping("/passport")
    public ResponseEntity<?> creatingPassport(@RequestBody CreatingPassportResponse creatingPassportResponse, Principal principal){
        return ResponseEntity.ok(passportService.createPassport(creatingPassportResponse, principal));
    }

    @GetMapping("/passport")
    public ResponseEntity<?> getPassport(Principal principal){
        return ResponseEntity.ok(passportService.getPassport(principal));
    }

    @PutMapping("/passport")
    public ResponseEntity<?> changePassport(@RequestBody CreatingPassportResponse creatingPassportResponse, Principal principal) {
        return ResponseEntity.ok(passportService.changePassport(creatingPassportResponse, principal));
    }
}
