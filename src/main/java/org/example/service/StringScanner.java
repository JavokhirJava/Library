package org.example.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class StringScanner {
    private Scanner scanner = new Scanner(System.in);

    public Scanner getStringScanner(){
        return scanner;
    }
}
