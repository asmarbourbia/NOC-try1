package com.hbdev.appointmentservice.client;


import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", url = "http://localhost:8081") // URL du patient-service
public interface PatientClient {

    @GetMapping("/api/patients/{id}")
        // Endpoint pour récupérer un patient par son ID
    PatientResponse getPatientById(@PathVariable Long id);

    @Getter
    @Setter
    class PatientResponse {
        private Long id;
        private String firstName;
        private String lastName;
        // Ajoutez d'autres champs si nécessaire
    }
}
