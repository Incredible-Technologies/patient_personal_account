package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Appointment;
import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndDate(Doctor doctor, Date date);

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByUserAndDate(User user, Date date);

    List<Appointment> findByUser(User user);

    @Query("SELECT appointment FROM Appointment appointment WHERE appointment.date > :date AND appointment.user = :user")
    List<Appointment> findAppointmentsByDateAfter(@Param("date") Date date, @Param("user") User user);

    @Query("SELECT appointment FROM Appointment appointment WHERE appointment.date < :date AND appointment.user = :user")
    List<Appointment> findAppointmentsByDateBefore(@Param("date") Date date, @Param("user") User user);

    @Query("SELECT appointment FROM Appointment appointment WHERE appointment.date = :date AND appointment.user = :user")
    List<Appointment> findAppointmentsByDateEquals(@Param("date") Date date, @Param("user") User user);
}
