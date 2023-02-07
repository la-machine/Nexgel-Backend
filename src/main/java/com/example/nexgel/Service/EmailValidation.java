package com.example.nexgel.Service;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidation implements Predicate<String>{
    @Override
    public boolean test(String s) {
        return true;
    }

}
