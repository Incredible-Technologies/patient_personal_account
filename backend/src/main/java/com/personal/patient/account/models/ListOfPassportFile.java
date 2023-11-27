package com.personal.patient.account.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@AllArgsConstructor
public class ListOfPassportFile {
    private List<Long> passportFileList;
}
