package com.techelevator.dao;

import com.techelevator.model.Adopter;

public interface AdopterDao {
    public Adopter insertAdopter(Adopter adopter);

    public Adopter getAdopterById(long id);
}
