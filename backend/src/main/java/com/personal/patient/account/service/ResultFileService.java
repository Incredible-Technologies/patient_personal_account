package com.personal.patient.account.service;

import com.personal.patient.account.entities.ResultFile;
import com.personal.patient.account.repositories.ResultFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ResultFileService {
    private final ResultFileRepository resultFileRepository;

    public ResultFile multipartFileToResultFile(MultipartFile file) throws IOException {
        ResultFile resultFile = new ResultFile();
        resultFile.setName(file.getName());
        resultFile.setOriginalFileName(file.getOriginalFilename());
        resultFile.setContentType(file.getContentType());
        resultFile.setSize(file.getSize());
        resultFile.setBytes(file.getBytes());
        return resultFile;
    }

    public void saveResultFile(ResultFile resultFile){
        resultFileRepository.save(resultFile);
    }
}
