package com.hbdev.appointmentservice.web;

import com.hbdev.appointmentservice.entity.Appointment;
import com.hbdev.appointmentservice.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    // DTO (Data Transfer Object) pour la requête
    public static class AppointmentRequest {
        private Long nurseId;
        private Long patientId;
        private String date;
        private String time;

        // Getters et Setters
        public Long getNurseId() { return nurseId; }
        public void setNurseId(Long nurseId) { this.nurseId = nurseId; }

        public Long getPatientId() { return patientId; }
        public void setPatientId(Long patientId) { this.patientId = patientId; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
    }

    // Nouvelle version avec body JSON
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequest request) {
        LocalDate appointmentDate = LocalDate.parse(request.getDate());
        LocalTime appointmentTime = LocalTime.parse(request.getTime());

        Appointment appointment = schedulingService.createAppointment(
                request.getNurseId(),
                request.getPatientId(),
                appointmentDate,
                appointmentTime
        );

        return ResponseEntity.ok(appointment);
    }
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = schedulingService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }
}