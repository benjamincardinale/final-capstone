package com.techelevator.controller;

import com.techelevator.Application;
import com.techelevator.dao.JdbcAdopterDao;
import com.techelevator.dao.JdbcPetDao;
import com.techelevator.model.*;
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
    private JdbcAdopterDao jdbcAdopterDao;
    private RestTemplate restTemplate = new RestTemplate();

    public PetController(JdbcPetDao jdbcPetDao, JdbcAdopterDao jdbcAdopterDao) {
        this.jdbcPetDao = jdbcPetDao;
        this.jdbcAdopterDao = jdbcAdopterDao;

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
    public void updatePet(@Valid @RequestBody PetWithImage petWithImage, @PathVariable long petId) {
        petWithImage.setId(petId);
        Pet pet = petWithImage.getPet(null);
        if (petWithImage.getImage() == null) {
            pet.setImageUrl(jdbcPetDao.getPetFromId(pet.getId()).getImageUrl());
        } else {
            try {
                pet.setImageUrl(addImage(petWithImage.getImage()));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        jdbcPetDao.updatePet(pet);
    }

    @RequestMapping(path = "/pet/update/adopter", method = RequestMethod.PUT)
    public void adoptedPet(@Valid @RequestBody Adopter adopter) {
        Pet pet = jdbcPetDao.getPetFromId(adopter.getPetId());
        pet.setAdopted(true);
        jdbcPetDao.updatePet(pet);
        jdbcAdopterDao.insertAdopter(adopter);
    }


}
