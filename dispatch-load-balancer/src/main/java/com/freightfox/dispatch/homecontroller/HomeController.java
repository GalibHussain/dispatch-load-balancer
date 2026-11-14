package com.freightfox.dispatch.homecontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "FreightFox Dispatch Load Balancer API is Running Successfully!";
    }
}
