package com.personal.patient.account.service;

import com.personal.patient.account.entities.Passport;
import com.personal.patient.account.entities.PassportFile;
import com.personal.patient.account.entities.User;
import com.personal.patient.account.exceptions.ForbiddenException;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.DeletePassportFile;
import com.personal.patient.account.models.ListOfPassportFile;
import com.personal.patient.account.models.PassportFileId;
import com.personal.patient.account.repositories.PassportFileRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassportFileService {
    private final PassportFileRepository passportFileRepository;

    private final PassportService passportService;

    private final UserService userService;

    public PassportFile newMultipartFileToPassportFile(MultipartFile file) throws IOException {
        PassportFile passportFile = new PassportFile();
        passportFile.setName(file.getName());
        passportFile.setOriginalFileName(file.getOriginalFilename());
        passportFile.setContentType(file.getContentType());
        passportFile.setSize(file.getSize());
        passportFile.setBytes(file.getBytes());
        return passportFile;
    }

    public PassportFileId addPassportFileRepository(Principal principal, MultipartFile file) throws IOException {
        User user = userService.getUserByPrincipal(principal);
        Passport passport = passportService.findByUser(user).orElse(new Passport());
        PassportFile passportFile = newMultipartFileToPassportFile(file);
        passport.getPassportFiles().add(passportFile);
        passportFile.setPassport(passport);
        passportService.save(passport);
        PassportFile passportFile1 = passportFileRepository.save(passportFile);
        return new PassportFileId(passportFile1.getId());
    }

    public ListOfPassportFile getPassportFiles(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Passport passport = passportService.findByUser(user).orElseThrow(
                () -> new NotFoundException("user with email " + principal.getName() + " did not have passport")
        );
        List<Long> listOfIdFiles = passport.getPassportFiles().stream().map(PassportFile::getId).collect(Collectors.toList());
        return new ListOfPassportFile(listOfIdFiles);
    }

    public DeletePassportFile deletePassportFile(Long passportFileId, Principal principal){
        PassportFile passportFile = passportFileRepository.findById(passportFileId).orElseThrow(
                () -> new NotFoundException("No passport file with id: " + passportFileId)
        );
        User user = userService.getUserByPrincipal(principal);
        if(!user.getPassport().getPassportFiles().contains(passportFile)){
            throw new ForbiddenException("Access denied");
        }
        passportFileRepository.myDeleteById(passportFileId);
        return new DeletePassportFile(passportFileId);
    }


    public PassportFile findById(Long id){
        return passportFileRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No passport file with id: " + id)
        );
    }
}
