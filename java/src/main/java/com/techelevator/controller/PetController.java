package com.techelevator.controller;

import com.techelevator.dao.AdopterDao;
import com.techelevator.dao.PetDao;
import com.techelevator.model.Adopter;
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

    private PetDao petDao;
    private AdopterDao adopterDao;
    private RestTemplate restTemplate = new RestTemplate();

    public PetController(PetDao petDao, AdopterDao adopterDao) {
        this.petDao = petDao;
        this.adopterDao = adopterDao;

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Pet> getAllPets() {

        return this.petDao.getAllPets();
    }

    private String addImage(String imageBytes) throws NullPointerException {
        String url = "https://api.imgbb.com/1/upload?key=cf122104a5dfbf471b70ae94aea0eacd";
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("image", imageBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<ImageDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ImageDto.class);
        return response.getBody().getDataUrl();
    }


    @RequestMapping(path = "/pet/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pet addPet(@Valid @RequestBody PetWithImage pet) {
        String imageUrl = null;
        try {
            imageUrl = addImage(pet.getImage());
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return petDao.addPet(pet.getPet(imageUrl));
    }

    @RequestMapping(path = "/pet/update/{petId}", method = RequestMethod.PUT)
    public void updatePet(@Valid @RequestBody PetWithImage petWithImage, @PathVariable long petId) {
        petWithImage.setId(petId);
        Pet pet = petWithImage.getPet(null);
        if (petWithImage.getImage() == null) {
            pet.setImageUrl(petDao.getPetFromId(pet.getId()).getImageUrl());
        } else {
            try {
                pet.setImageUrl(addImage(petWithImage.getImage()));
            } catch (NullPointerException e) {
                System.out.println(e);
            }
        }
        petDao.updatePet(pet);
    }

    @RequestMapping(path = "/pet/update/adopter", method = RequestMethod.PUT)
    public void adoptedPet(@Valid @RequestBody Adopter adopter) {
        Pet pet = petDao.getPetFromId(adopter.getPetId());
        pet.setAdopted(true);
        petDao.updatePet(pet);
        adopterDao.insertAdopter(adopter);
    }


}
