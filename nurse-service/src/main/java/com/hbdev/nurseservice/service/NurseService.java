package com.hbdev.nurseservice.service;

import com.hbdev.nurseservice.client.UserClient;
import com.hbdev.nurseservice.entity.Nurse;
import com.hbdev.nurseservice.repository.NurseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.text.ParseException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NurseService {

    private final NurseRepository nurseRepository;
    private final UserClient userClient;

    public Nurse createNurse(Nurse nurse, MultipartFile profilePhoto,
                             MultipartFile insuranceDocument, MultipartFile cinDocument) throws IOException {

        // Validation de l'utilisateur
        UserClient.UserResponse user = userClient.getUserById(nurse.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!"nurse".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("User role is not nurse");
        }

        // Gestion des fichiers
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            nurse.setProfilePhotoPath(saveFile(profilePhoto, "profimg"));
        }
        if (insuranceDocument != null && !insuranceDocument.isEmpty()) {
            nurse.setInsuranceDocumentPath(saveFile(insuranceDocument, "insurance_doc"));
        }
        if (cinDocument != null && !cinDocument.isEmpty()) {
            nurse.setCinDocumentPath(saveFile(cinDocument, "cin"));
        }

        return nurseRepository.save(nurse);
    }

    private String saveFile(MultipartFile file, String directory) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path storagePath = Paths.get("C:\\Users\\HoudHoud Belhad\\Documents\\data_nurse\\" + directory);

        if (!Files.exists(storagePath)) {
            Files.createDirectories(storagePath);
        }

        Path targetPath = storagePath.resolve(fileName);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetPath.toString();
    }
    public Nurse updateNurse(Long id, Nurse nurse, MultipartFile profilePhoto,
                             MultipartFile insuranceDocument, MultipartFile cinDocument) throws IOException, ParseException {

        // Récupérer le nurse existant
        Nurse existingNurse = nurseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nurse not found"));

        // Mettre à jour les champs uniquement s'ils ne sont pas null
        if (nurse.getName() != null) {
            existingNurse.setName(nurse.getName());
        }
        if (nurse.getPhoneNumber() != null) {
            existingNurse.setPhoneNumber(nurse.getPhoneNumber());
        }
        if (nurse.getAddress() != null) {
            existingNurse.setAddress(nurse.getAddress());
        }
        if (nurse.getDateOfBirth() != null) {
            // Convertir la String en Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(nurse.getDateOfBirth().toString()); // Assurez-vous que getDateOfBirth() retourne une String
            existingNurse.setDateOfBirth(parsedDate);
        }
        if (nurse.getSex() != null) {
            existingNurse.setSex(nurse.getSex());
        }
        if (nurse.getProfessionalId() != null) {
            existingNurse.setProfessionalId(nurse.getProfessionalId());
        }
        if (nurse.getLanguages() != null) {
            existingNurse.setLanguages(nurse.getLanguages());
        }
        if (nurse.getGeographicArea() != null) {
            existingNurse.setGeographicArea(nurse.getGeographicArea());
        }

        // Gestion des fichiers
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            existingNurse.setProfilePhotoPath(saveFile(profilePhoto, "profimg"));
        }
        if (insuranceDocument != null && !insuranceDocument.isEmpty()) {
            existingNurse.setInsuranceDocumentPath(saveFile(insuranceDocument, "insurance_doc"));
        }
        if (cinDocument != null && !cinDocument.isEmpty()) {
            existingNurse.setCinDocumentPath(saveFile(cinDocument, "cin"));
        }

        return nurseRepository.save(existingNurse);
    }
    public Nurse getNurseById(Long id) {
        return nurseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nurse not found"));
    }
    // Méthode pour récupérer tous les infirmiers
    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }


}