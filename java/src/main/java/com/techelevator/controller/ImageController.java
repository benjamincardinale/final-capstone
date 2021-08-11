package com.techelevator.controller;



import com.techelevator.model.Image;
import com.techelevator.model.ImageDto;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ImageController {


   private final RestTemplate restTemplate = new RestTemplate();

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

