package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
