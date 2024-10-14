package com.booking.controller;

import com.booking.entity.Favourite;
import com.booking.entity.PropertyUser;
import com.booking.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/air5/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @PostMapping
    public ResponseEntity<Favourite> addFavourite(
            @RequestBody Favourite favourite,
            @AuthenticationPrincipal PropertyUser user)
    {
        favourite.setPropertyUser(user);
        Favourite savedFavourite = favouriteRepository.save(favourite);

        return new ResponseEntity<>(savedFavourite, HttpStatus.CREATED);
    }

}
