package com.gustaa13.springBootInitialStudies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gustaa13.springBootInitialStudies.domain.User;
import com.gustaa13.springBootInitialStudies.service.HelloWorldService;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    /*private HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }*/

    // Or

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    public String helloWorld() {
        return helloWorldService.HelloWorld("Gustavo");
    }

    @PostMapping
    public String helloWorldPost(@RequestBody User body) {
        return "Hello, " + body.getName() + ". Você possui o email: " + body.getEmail() + ".";
    }

    @PostMapping("/{id}")
    public String helloWorldPost(@RequestParam(value = "filter", defaultValue = "nenhum") String filter, @PathVariable("id") String id, @RequestBody User body) {
        return "Hello, " + body.getName() + ". O seu email é: " + body.getEmail() + ". O seu ID é: " + id + ". O seu filtro é: " + filter + ".";
    }
}
