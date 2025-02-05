package com.hbdev.appointmentservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long nurseId; // ID du nurse

    @Column(nullable = false)
    private Long patientId; // ID du patient

    @Column(nullable = false)
    private LocalDate date; // Date du rendez-vous

    @Column(nullable = false)
    private LocalTime time; // Heure du rendez-vous

    @Column(nullable = false)
    private String status; // Statut du rendez-vous (ex: "planned", "completed", "cancelled")
}