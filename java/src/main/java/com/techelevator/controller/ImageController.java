package com.techelevator.controller;

import com.techelevator.dto.ImageAPIDto;
import com.techelevator.model.Image;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ImageController {
    private static final String IMAGE_API_KEY = "cf122104a5dfbf471b70ae94aea0eacd";

    public ImageController() {

    }

    @RequestMapping(path = "pet/add/image", method = RequestMethod.POST)
    public void uploadImage(@Valid @RequestBody Image image) {
        System.out.println(image);
    }
}
