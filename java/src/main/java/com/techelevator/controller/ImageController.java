package com.techelevator.controller;

import java.io.*;
import java.util.Arrays;

import com.techelevator.model.Pet;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ImageController {


   private RestTemplate restTemplate = new RestTemplate();



   /* @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String addImage(@Valid @RequestBody InputStream image) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<InputStream> entity = new HttpEntity<>(image, headers);

        return restTemplate.exchange(
                "https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd", HttpMethod.POST, entity, String.class).getBody();
    } */

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public byte[] addImage(@Valid @RequestBody InputStream image) throws IOException {
        System.out.println("This is a test to see if the app is even getting to this point");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Content-Type", "image/jpg");
        byte[] media = image.readAllBytes();
        HttpEntity<byte[]> entity = new HttpEntity<>(media, headers);

        return restTemplate.exchange(
                "https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd", HttpMethod.POST, entity, byte[].class).getBody();
    }

}

