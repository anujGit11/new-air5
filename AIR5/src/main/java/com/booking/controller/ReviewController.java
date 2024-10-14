package com.booking.controller;

import com.booking.entity.Property;
import com.booking.entity.PropertyUser;
import com.booking.entity.Review;
import com.booking.repository.PropertyRepository;
import com.booking.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/air5/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;


    @Autowired
    private PropertyRepository propertyRepository;



    @PostMapping("/addReview/{propertyId}")
    public ResponseEntity<String> addReview(
            @PathVariable long propertyId,
            @RequestBody Review review,
            @AuthenticationPrincipal PropertyUser user)
    {
        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();

        Review r = reviewRepository.findReviewByUser(property, user);
        if(r!= null){
            return new ResponseEntity<>("Review already added",HttpStatus.BAD_REQUEST);
        }

        review.setProperty(property);
        review.setPropertyUser(user);

        reviewRepository.save(review);
        return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/userReview")
    public ResponseEntity<List<Review>> getUserReviews(@AuthenticationPrincipal PropertyUser user){
        List<Review> reviews = reviewRepository.findReviewsByPropertyUser(user);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

}
