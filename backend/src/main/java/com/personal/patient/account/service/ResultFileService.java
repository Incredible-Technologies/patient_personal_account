package com.personal.patient.account.service;

import com.personal.patient.account.entities.ResultCard;
import com.personal.patient.account.entities.ResultFile;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.repositories.ResultFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ResultFileService {
    private final ResultFileRepository resultFileRepository;

    public ResultFile newMultipartFileToResultFile(MultipartFile file) throws IOException {
        ResultFile resultFile = new ResultFile();
        resultFile.setName(file.getName());
        resultFile.setOriginalFileName(file.getOriginalFilename());
        resultFile.setContentType(file.getContentType());
        resultFile.setSize(file.getSize());
        resultFile.setBytes(file.getBytes());
        return resultFile;
    }

    public void setMultipartFileToResultFile(ResultFile resultFile, MultipartFile file) throws IOException {
        resultFile.setName(file.getName());
        resultFile.setOriginalFileName(file.getOriginalFilename());
        resultFile.setContentType(file.getContentType());
        resultFile.setSize(file.getSize());
        resultFile.setBytes(file.getBytes());
    }

    public void saveResultFile(ResultFile resultFile){
        resultFileRepository.save(resultFile);
    }

    public ResultFile findByResultCard(ResultCard resultCard){
        return resultFileRepository.findByResultCard(resultCard).orElseThrow(() -> new NotFoundException("result file with card id " + resultCard.getId() + " not found"));
    }

    public ResultFile findByResultCardNew(ResultCard resultCard){
        return resultFileRepository.findByResultCard(resultCard).orElseGet(ResultFile::new);
    }
}
