package com.rozkurt.ss_2022_c5_e1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "Demo";
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/open")
    public String open() {
        return "Open";
    }


}
