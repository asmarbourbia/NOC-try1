package com.hbdev.appointmentservice.service;

import com.hbdev.appointmentservice.client.NurseClient;
import com.hbdev.appointmentservice.client.PatientClient;
import com.hbdev.appointmentservice.client.NotificationClient;
import com.hbdev.appointmentservice.entity.Appointment;
import com.hbdev.appointmentservice.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SchedulingService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private NurseClient nurseClient;

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private NotificationClient notificationClient; // Injecter NotificationClient

    public Appointment createAppointment(Long nurseId, Long patientId, LocalDate date, LocalTime time) {
        // Vérifier que le nurse existe
        NurseClient.NurseResponse nurse = nurseClient.getNurseById(nurseId);
        if (nurse == null) {
            throw new IllegalArgumentException("Nurse not found");
        }

        // Vérifier que le patient existe
        PatientClient.PatientResponse patient = patientClient.getPatientById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }

        // Créer le rendez-vous
        Appointment appointment = new Appointment();
        appointment.setNurseId(nurseId);
        appointment.setPatientId(patientId);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setStatus("planned");

        // Enregistrer le rendez-vous
        Appointment savedAppointment = appointmentRepository.save(appointment);

        // Créer une notification pour le rendez-vous
        String message = String.format(
                "Nouveau rendez-vous planifié avec %s (Infirmier) pour %s %s (Patient) le %s à %s.",
                nurse.getName(),
                patient.getFirstName(),
                patient.getLastName(),
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                time.format(DateTimeFormatter.ofPattern("HH:mm"))
        );
        notificationClient.createNotification(patientId, message);
        return savedAppointment;
    }
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll(); // Récupérer tous les rendez-vous
    }
}