package org.example.dataBase;

import org.example.dto.User;
import org.example.enums.Role;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitDataBase {
    @Autowired
    UserRepository userRepository;
    public void adminInit(){
        User user = new User();
        user.setName("Admin");
        user.setSurname("Adminjon");
        user.setCreatedDate(LocalDate.now());
        user.setVisible(true);
        user.setRole(Role.ADMIN);
        user.setPhone("1111");

        User user1 = userRepository.getUserByPhone(user.getPhone());
        if (user1 != null){
            System.out.println("Bunday admin Bor");
            return;
        }
        userRepository.saveUser(user);
    }
}
