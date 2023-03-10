package org.example.service;

import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.dto.User;
import org.example.enums.Role;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminController adminController;
    @Autowired
    UserController userController;

    public void login(String phone) {
        User user = userRepository.getUserByPhone(phone);
        if (user == null) {
            System.out.println("User with this number does not exist !!!");
        } else if (user.getRole().equals(Role.ADMIN)) {
            adminController.start();
        } else if (user.getRole().equals(Role.STUDENT)) {
            userController.start();
        }
    }
}
