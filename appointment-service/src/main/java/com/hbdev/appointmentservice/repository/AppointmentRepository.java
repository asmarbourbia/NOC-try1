package com.hbdev.appointmentservice.repository;


import com.hbdev.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByNurseId(Long nurseId); // Récupérer les rendez-vous d'un nurse
    List<Appointment> findByPatientId(Long patientId); // Récupérer les rendez-vous d'un patient
    List<Appointment> findByDate(LocalDate date); // Récupérer les rendez-vous pour une date donnée
}
