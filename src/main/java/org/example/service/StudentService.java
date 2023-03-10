package org.example.service;

import org.example.dto.User;
import org.example.enums.Role;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class StudentService {
    @Autowired
    UserRepository userRepository;
    public void addStudent(User user) {
        User user1 = userRepository.getUserByPhone(user.getPhone());
        if(user1 != null){
            System.out.println("User with this number is exist !!!");
            return;
        }
        userRepository.addStudent(user);
        System.out.println("Student added successfully\n");
    }

    public void studentList() {
        LinkedList<User> users = userRepository.getStudentList();
        for (User user : users){
            System.out.println(user);
        }
    }

    public void deleteStudent(Integer id) {
        User user = userRepository.getById(id);
        if(!user.getRole().equals(Role.ADMIN)){
            userRepository.deleteStudent(id);
            System.out.println("Student deleted successfully");
            return;
        }
        System.out.println("Can not delete this user or not exist");
    }

    public void activateStudent(Integer id) {
        User user = userRepository.getById(id);
        if(!user.getRole().equals(Role.ADMIN)){
            userRepository.studentActivation(id);
            System.out.println("Student activated successfully");
            return;
        }
        System.out.println("Can not activate this user or not exist");
    }
}
