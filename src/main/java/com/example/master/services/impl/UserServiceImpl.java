package com.example.master.services.impl;

import com.example.master.Dto.UserDTO;
import com.example.master.exception.NotFoundException;
import com.example.master.model.User;
import com.example.master.repository.UserRepository;
import com.example.master.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getMobile(),
                user.getPassword(),
                user.getRole(),
                user.getDistrict(),
                user.getProject()
        );
    }

    private User mapToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setDistrict(dto.getDistrict());
        user.setProject(dto.getProject());
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return mapToDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        return mapToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setDistrict(userDTO.getDistrict());
        user.setProject(userDTO.getProject());
        return mapToDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new NotFoundException("User not found");
        userRepository.deleteById(id);
    }
}
