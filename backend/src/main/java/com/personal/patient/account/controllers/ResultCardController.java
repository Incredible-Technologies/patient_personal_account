package com.personal.patient.account.controllers;

import com.personal.patient.account.entities.ResultCard;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingResultCardRequest;
import com.personal.patient.account.models.CreatingResultCardResponse;
import com.personal.patient.account.models.DownloadResultFile;
import com.personal.patient.account.service.ResultCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result-card")
public class ResultCardController {
    private final ResultCardService resultCardService;

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createResultCard(@RequestBody CreatingResultCardResponse resultCardResponse){
        ResultCard resultCard = resultCardService.createResultCard(resultCardResponse);
        return ResponseEntity.ok(new CreatingResultCardRequest(resultCardResponse, resultCard.getId()));
    }

    @PostMapping("/download")
    public ResponseEntity<?> downloadResultFile(@RequestParam("file") MultipartFile file, @RequestParam("cardId") Long cardId) throws IOException {
        resultCardService.saveResultFile(file, cardId);
        return ResponseEntity.ok(new DownloadResultFile(cardId));
    }
}
