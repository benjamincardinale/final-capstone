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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ImageController {


   private final RestTemplate restTemplate = new RestTemplate();


    /*@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public String addImage(@Valid InputStream image) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<InputStream> entity = new HttpEntity<>(image, headers);

        return restTemplate.exchange(
                "https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd", HttpMethod.POST, entity, String.class).getBody();
    } */

    /*@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public byte[] addImage(InputStream image) throws IOException {
        System.out.println("This is a test to see if the app is even getting to this point");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        byte[] media = image.readAllBytes();
        HttpEntity<byte[]> entity = new HttpEntity<>(media, headers);

        return restTemplate.exchange(
                "https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd", HttpMethod.POST, entity, byte[].class).getBody();
    } */

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public String addImage(MultipartFile formData){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.MULTIPART_FORM_DATA));
        HttpEntity<MultipartFile> entity = new HttpEntity<MultipartFile>(formData, headers);

        return restTemplate.exchange("https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd", HttpMethod.POST, entity, String.class).getBody();
    }

}

