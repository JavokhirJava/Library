package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class NumScanner {
    private Scanner scanner = new Scanner(System.in);

    public Scanner getNumScanner(){
        return scanner;
    }
}
