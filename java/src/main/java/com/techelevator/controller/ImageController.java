package com.techelevator.controller;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;

import com.techelevator.model.Image;
import com.techelevator.model.ImageDto;
import com.techelevator.model.Pet;
import jdk.jfr.ContentType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
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

    /*
    //Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.web.client.HttpClientErrorException$BadRequest: 400 Bad Request: [{"status_code":400,"error":{"message":"Empty upload source.","code":130,"context":"Exception"}
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public byte[] addImage(@RequestBody InputStream image) throws IOException {
        System.out.println("This is a test to see if the app is even getting to this point");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        byte[] media = image.readAllBytes();
        HttpEntity<byte[]> entity = new HttpEntity<>(media, headers);

        return restTemplate.exchange(
                "https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd", HttpMethod.POST, entity, byte[].class).getBody();
    }
    */


    // "Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.web.client.HttpClientErrorException$BadRequest: 400 Bad Request
    /*
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public String addImage(MultipartFile formData){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.MULTIPART_FORM_DATA));
        HttpEntity<MultipartFile> entity = new HttpEntity<MultipartFile>(formData, headers);

        return restTemplate.exchange("https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd", HttpMethod.POST, entity, String.class).getBody();
    }

     */

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addImage(@RequestBody Image imageBytes) throws Exception {
        //System.out.println(imageBytes.getImage());
        String url = "https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd";
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("image", imageBytes.getImage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);

        ResponseEntity<ImageDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ImageDto.class);

        

        return response.getBody().getDataUrl();
    }

}

