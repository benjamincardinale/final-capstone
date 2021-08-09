package com.techelevator.controller;

import com.techelevator.Application;
import com.techelevator.dao.JdbcPetDao;
import com.techelevator.model.Image;
import com.techelevator.model.ImageDto;
import com.techelevator.model.Pet;
import com.techelevator.model.PetWithImage;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PetController {
    private JdbcPetDao jdbcPetDao;
    private RestTemplate restTemplate;

    public PetController(JdbcPetDao jdbcPetDao, RestTemplate restTemplate) {
        this.jdbcPetDao = jdbcPetDao;
        this.restTemplate = restTemplate;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path="/", method = RequestMethod.GET)
    public List<Pet> getAllPets(){
        return this.jdbcPetDao.getAllPets();
    }

    private String addImage(String imageBytes) throws Exception {
        //System.out.println(imageBytes.getImage());
        String url = "https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd";
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("image", imageBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);

        ResponseEntity<ImageDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ImageDto.class);



        return response.getBody().getDataUrl();
    }

    //@ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/pet/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pet addPet(@Valid @RequestBody PetWithImage pet) {
        String imageUrl = null;
        try {
            imageUrl = addImage(pet.getImage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return jdbcPetDao.addPet(pet.getPet(imageUrl));
    }
    
    @RequestMapping(path = "/pet/update/{petId}", method = RequestMethod.PUT) //wew, come back to this one.
    public void updatePet(@Valid @RequestBody Pet pet, @PathVariable long petId) {
        pet.setId(petId);
        jdbcPetDao.updatePet(pet);
    }
}
