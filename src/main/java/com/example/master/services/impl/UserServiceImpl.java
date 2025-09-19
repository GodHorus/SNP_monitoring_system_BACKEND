package com.example.master.services.impl;

import com.example.master.Dto.PasswordUpdateDTO;
import com.example.master.Dto.UserProfileDTO;
import com.example.master.Dto.UserRequestDTO;
import com.example.master.model.User;
import com.example.master.repository.UserRepository;
import com.example.master.config.KeycloakUserService;
import com.example.master.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KeycloakUserService keycloakUserService;

    public UserServiceImpl(UserRepository userRepository, KeycloakUserService keycloakUserService) {
        this.userRepository = userRepository;
        this.keycloakUserService = keycloakUserService;
    }

    @Transactional
    public User createUser(User user, String password, String role, String firstName, String lastName) {
        // 1. Create user in Keycloak
        String keycloakUserId = keycloakUserService.createUser(
                user.getName(),
                user.getEmail(),
                password,
                role,
                firstName,   // send to Keycloak
                lastName     // send to Keycloak
        );

        // 2. Save in DB (without firstName and lastName)
        user.setKeycloakUserId(keycloakUserId);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUserRole(String userId, String oldRole, String newRole) {
        User user = userRepository.findByKeycloakUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        keycloakUserService.updateUserRole(user.getKeycloakUserId(), oldRole, newRole);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserProfileDTO getProfile(String keycloakUserId) {
        User user = userRepository.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDTO dto = new UserProfileDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getMobile());
        dto.setDistrict(user.getDistrict());
        dto.setCdpo(user.getCdpo());
        dto.setSectors(user.getSectors());
        return dto;
    }

    @Override
    @Transactional
    public void updateProfile(String keycloakUserId, UserProfileDTO profileDTO) {
        userRepository.updateUserProfile(
                keycloakUserId,
                profileDTO.getName(),
                profileDTO.getMobile(),
                profileDTO.getDistrict(),
                profileDTO.getCdpo(),
                profileDTO.getSectors()
        );
    }

    @Override
    public void updatePassword(String keycloakUserId, PasswordUpdateDTO dto) {
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        // delegate to Keycloak
        keycloakUserService.updateUserPassword(keycloakUserId, dto.getNewPassword());
    }

}
