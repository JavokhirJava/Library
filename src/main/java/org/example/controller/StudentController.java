package org.example.controller;

import org.example.dto.User;
import org.example.service.NumScanner;
import org.example.service.StringScanner;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {
    @Autowired
    private NumScanner numScanner;
    @Autowired
    private StringScanner stringScanner;
    @Autowired
    private StudentService studentService;
    public void start() {
        boolean b = true;
        while (b){
            switch (menu()){
                case 1 -> studentList();
                case 2 -> addStudent();
                case 3 -> deleteStudent();
                case 4 -> activateStudent();
                default -> b=false;
            }
        }
    }

    private void activateStudent() {
        System.out.println("Please enter student id ");
        Integer id = numScanner.getNumScanner().nextInt();
        studentService.activateStudent(id);
    }

    private void deleteStudent() {
        System.out.println("Please enter student id ");
        Integer id = numScanner.getNumScanner().nextInt();
        studentService.deleteStudent(id);
    }

    private void addStudent() {
        System.out.println("Enter name :");
        String name = stringScanner.getStringScanner().next();
        System.out.println("Enter surname :");
        String surname = stringScanner.getStringScanner().next();
        System.out.println("Enter phone:");
        String phone = stringScanner.getStringScanner().next();

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);

        studentService.addStudent(user);
    }

    private void studentList() {
        studentService.studentList();
    }

    private Integer menu() {
        System.out.println("1.Student List");
        System.out.println("2.Add Student");
        System.out.println("3.Delete Student");
        System.out.println("4.Student Activation");
        System.out.println("Choose :");
        return numScanner.getNumScanner().nextInt();
    }
}
