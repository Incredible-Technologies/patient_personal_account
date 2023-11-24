package com.personal.patient.account.controllers;

import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingSnilsResponse;
import com.personal.patient.account.service.SnilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentsController {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка валидации: поле user_id уже существует");
    }
    private final SnilsService snilsService;

    @PostMapping("/snils")
    public ResponseEntity<?> creatingSnil(@RequestBody CreatingSnilsResponse creatingSnilsResponse, Principal principal){
        return ResponseEntity.ok(snilsService.createSnils(creatingSnilsResponse, principal));
    }
}
