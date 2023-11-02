package com.personal.patient.account.controllers;

import com.personal.patient.account.entities.ResultCard;
import com.personal.patient.account.entities.ResultFile;
import com.personal.patient.account.service.ResultCardService;
import com.personal.patient.account.service.ResultFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final ResultCardService resultCardService;
    private final ResultFileService resultFileService;

    @GetMapping("/result-file/{cardId}")
    private ResponseEntity<?> getResultFile(@PathVariable Long cardId){
        ResultCard resultCard = resultCardService.findById(cardId);
        ResultFile resultFile = resultFileService.findByResultCard(resultCard);
        return ResponseEntity.ok()
                .header("fileName", resultFile.getOriginalFileName())
                .contentType(MediaType.valueOf(resultFile.getContentType()))
                .contentLength(resultFile.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(resultFile.getBytes())));
    }
}
