package com.jinloes.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jinloes on 1/13/16.
 */
@RestController
public class PollController {
    @RequestMapping("/")
    public String index() {
        System.out.println("test");
        return "Greetings from Spring Boot!";
    }
}
