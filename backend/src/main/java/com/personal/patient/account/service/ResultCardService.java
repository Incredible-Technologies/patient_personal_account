package com.personal.patient.account.service;

import com.personal.patient.account.entities.ResultCard;
import com.personal.patient.account.entities.ResultFile;
import com.personal.patient.account.entities.User;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingResultCardResponse;
import com.personal.patient.account.repositories.ResultCardRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultCardService {
    private final ResultCardRepository resultCardRepository;
    private final ResultFileService resultFileService;
    private final UserService userService;
    private final DateUtils dateUtils;


    public ResultCard createResultCard(CreatingResultCardResponse resultCardResponse){
        User dbUser = userService.getUserByEmail(resultCardResponse.getUserEmail());

        ResultCard resultCard = new ResultCard();
        resultCard.setDateOfMake(new Date());

        Date dateOfShouldReady = dateUtils.parseStringToDate(resultCardResponse.getDateOfShouldReady());
        resultCard.setDateOfShouldReady(dateOfShouldReady);
        resultCard.setDescription(resultCardResponse.getDescription());
        resultCard.setHospitalAddress(resultCardResponse.getHospitalAddress());

        resultCard.setUser(dbUser);

        resultCardRepository.save(resultCard);
        return resultCard;
    }

    public ResultCard findById(Long cardId){
        return resultCardRepository.findById(cardId).orElseThrow(() -> new NotFoundException("Card with id " + cardId + " not found"));
    }

    public List<ResultCard> findAllByUser(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        return resultCardRepository.findByUserOrderByDateOfShouldReadyAsc(user);
    }

    public List<ResultCard> findAllDoneByUser(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        return resultCardRepository.findByUserAndDateOfDeliveredIsNotNullOrderByDateOfShouldReadyAsc(user);
    }

    public List<ResultCard> findAllNotDoneByUser(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        return resultCardRepository.findByUserAndDateOfDeliveredIsNullOrderByDateOfShouldReadyAsc(user);
    }

    public void saveResultFile(MultipartFile file, Long cardId) throws IOException {
        ResultCard savedResultCard = findById(cardId);
        savedResultCard.setDateOfDelivered(new Date());
        ResultFile dbResultFile = resultFileService.findByResultCardNew(savedResultCard);
        if(dbResultFile.equals(new ResultFile())){
            dbResultFile = resultFileService.newMultipartFileToResultFile(file);
        }
        else {
            resultFileService.setMultipartFileToResultFile(dbResultFile,file);
        }
        dbResultFile.setResultCard(savedResultCard);
        resultFileService.saveResultFile(dbResultFile);
        savedResultCard.setResultFile(dbResultFile);
        resultCardRepository.save(savedResultCard);
    }
}
