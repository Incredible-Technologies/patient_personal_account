package com.personal.patient.account.service;

import com.personal.patient.account.entities.ResultCard;
import com.personal.patient.account.entities.ResultFile;
import com.personal.patient.account.entities.User;
import com.personal.patient.account.models.CreatingResultCardResponse;
import com.personal.patient.account.models.DownloadResultFile;
import com.personal.patient.account.repositories.ResultCardRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ResultCardService {
    private final ResultCardRepository resultCardRepository;
    private final ResultFileService resultFileService;
    private final UserSrevice userSrevice;

    private final DateUtils dateUtils;

    public ResultCard createResultCard(CreatingResultCardResponse resultCardResponse){
        ResultCard resultCard = new ResultCard();
        resultCard.setDateOfMake(new Date());

        Date dateOfShouldReady = dateUtils.parseStringToDate(resultCardResponse.getDateOfShouldReady());
        resultCard.setDateOfShouldReady(dateOfShouldReady);
        resultCard.setDescription(resultCardResponse.getDescription());
        resultCard.setHospitalAddress(resultCardResponse.getHospitalAddress());

        User dbUser = userSrevice.getUserByEmail(resultCardResponse.getUserEmail());
        resultCard.setUser(dbUser);

        resultCardRepository.save(resultCard);
        return resultCard;
    }

    public ResultCard findById(Long cardId){
        return resultCardRepository.findById(cardId).orElseGet(ResultCard::new);
    }

    public void saveResultFile(MultipartFile file, Long cardId) throws IOException {
        ResultFile savedResultFile = resultFileService.multipartFileToResultFile(file);
        ResultCard savedResultCard = findById(cardId);
        savedResultFile.setResultCard(savedResultCard);
        resultFileService.saveResultFile(savedResultFile);
        savedResultCard.setResultFile(savedResultFile);
        resultCardRepository.save(savedResultCard);
    }
}
