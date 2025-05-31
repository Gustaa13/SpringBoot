package com.gustaa13.springBootInitialStudies.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String HelloWorld(String name) {
        return "Hello, " + name;
    }
}
