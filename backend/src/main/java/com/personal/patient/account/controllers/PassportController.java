package com.personal.patient.account.controllers;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.exceptions.ForbiddenException;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingPassportResponse;
import com.personal.patient.account.models.DeletePassportFile;
import com.personal.patient.account.service.PassportFileService;
import com.personal.patient.account.service.PassportService;
import com.personal.patient.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class PassportController {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка валидации: поле user_id уже существует");
    }
    private final PassportService passportService;

    private final PassportFileService passportFileService;

    private final UserService userService;

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


    @PostMapping("/passport/passport-file")
    public ResponseEntity<?> createPassportFile(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
        return ResponseEntity.ok(passportFileService.addPassportFileRepository(principal, file));
    }


    @GetMapping("/passport/passport-file")
    public ResponseEntity<?> getPassportFiles(Principal principal){
        return ResponseEntity.ok(passportFileService.getPassportFiles(principal));
    }


    @DeleteMapping("/passport/passport-file/{deleteId}")
    public ResponseEntity<?> DeletePassportFile(Principal principal, @PathVariable Long deleteId){
        DeletePassportFile deletePassportFile = passportFileService.deletePassportFile(deleteId, principal);
        return ResponseEntity.ok(deletePassportFile);
    }
}
