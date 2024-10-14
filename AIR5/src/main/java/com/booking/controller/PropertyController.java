package com.booking.controller;

import com.booking.entity.Property;
import com.booking.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/air5/properties")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;


    @GetMapping("{locationName}")
    public ResponseEntity<List<Property>> findProperty(@PathVariable String locationName){

        List<Property> properties = propertyRepository.findPropertyByLocation(locationName);
        return new ResponseEntity<>(properties, HttpStatus.OK);


    }

}
