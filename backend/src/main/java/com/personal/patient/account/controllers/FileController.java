package com.personal.patient.account.controllers;

import com.personal.patient.account.entities.PassportFile;
import com.personal.patient.account.entities.ResultCard;
import com.personal.patient.account.entities.ResultFile;
import com.personal.patient.account.exceptions.ForbiddenException;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.service.PassportFileService;
import com.personal.patient.account.service.ResultCardService;
import com.personal.patient.account.service.ResultFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final ResultCardService resultCardService;
    private final ResultFileService resultFileService;
    private final PassportFileService passportFileService;

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @GetMapping("/result-file/{cardId}")
    private ResponseEntity<?> getResultFile(@PathVariable Long cardId, Principal principal){
        ResultCard resultCard = resultCardService.findById(cardId);
        if(!resultCard.getUser().getEmail().equals(principal.getName())){
            throw new ForbiddenException("Access denied");
        }
        ResultFile resultFile = resultFileService.findByResultCard(resultCard);
        return ResponseEntity.ok()
                .header("fileName", resultFile.getOriginalFileName())
                .contentType(MediaType.valueOf(resultFile.getContentType()))
                .contentLength(resultFile.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(resultFile.getBytes())));
    }

    @GetMapping("/passport-file/{passportFileId}")
    private ResponseEntity<?> getPassportFile(@PathVariable Long passportFileId, Principal principal){
        PassportFile passportFile = passportFileService.findById(passportFileId);
        if(!passportFile.getPassport().getUser().getEmail().equals(principal.getName())){
            throw new ForbiddenException("Access denied");
        }
        return ResponseEntity.ok()
                .header("fileName", passportFile.getOriginalFileName())
                .contentType(MediaType.valueOf(passportFile.getContentType()))
                .contentLength(passportFile.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(passportFile.getBytes())));
    }
}
