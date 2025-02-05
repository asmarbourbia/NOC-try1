package com.hbdev.appointmentservice.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nurse-service", url = "http://localhost:8082") // URL du nurse-service
public interface NurseClient {

    @GetMapping("/api/nurses/{id}") // Endpoint pour récupérer un nurse par son ID
    NurseResponse getNurseById(@PathVariable Long id);

    @Getter
    @Setter
    class NurseResponse {
        private Long id;
        private String name;
        private String geographicArea;
        // Ajoutez d'autres champs si nécessaire
    }
}