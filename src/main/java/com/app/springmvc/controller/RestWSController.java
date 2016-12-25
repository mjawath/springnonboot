package com.app.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/v1")
public class RestWSController {

    @RequestMapping("/test")
    private String testRest() {
        System.out.println("=========================rest thisngsfsdfsdf");
        return "rest test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    private String testRestPost(String test) {
        System.out.println("========================= post rest thisngsfsdfsdf" + test);
        return "rest post test";
    }
}
