package com.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/air5/countries")
public class CountryController {

    @PostMapping("/addCountry")
    public String addCountry(){
        return "okay";
    }

}
