package com.personal.patient.account.service;

import com.personal.patient.account.entities.Hospital;
import com.personal.patient.account.models.CreatingHospitalRequest;
import com.personal.patient.account.models.CreatingHospitalResponse;
import com.personal.patient.account.repositories.HospitalRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    private final DateUtils dateUtils;

    public void save(Hospital hospital){
        hospitalRepository.save(hospital);
    }

    public CreatingHospitalRequest addHospital(CreatingHospitalResponse creatingHospitalResponse){
        Hospital hospital = new Hospital();
        hospital.setName(creatingHospitalResponse.getName());
        hospital.setAddress(creatingHospitalResponse.getAddress());
        hospital.setOpeningTime(dateUtils.parseStringToTime(creatingHospitalResponse.getOpeningTime()));
        hospital.setClosingTime(dateUtils.parseStringToTime(creatingHospitalResponse.getClosingTime()));
        hospital = hospitalRepository.save(hospital);
        return new CreatingHospitalRequest(hospital);
    }
}
