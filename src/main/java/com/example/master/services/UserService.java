package com.example.master.services;

import com.example.master.Dto.PasswordUpdateDTO;
import com.example.master.Dto.UserProfileDTO;
import com.example.master.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

//    User createUser(User user, String password, String role);

    User createUser(User user, String password, String role, String firstName, String lastName);

    void updateUserRole(String userId, String oldRole, String newRole);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    UserProfileDTO getProfile(String keycloakUserId);

    void updateProfile(String keycloakUserId, UserProfileDTO dto);

    void updatePassword(String keycloakUserId, PasswordUpdateDTO dto);

}
