package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Appointment;
import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndDate(Doctor doctor, Date date);

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByUserAndDate(User user, Date date);

    List<Appointment> findByUser(User user);
}
