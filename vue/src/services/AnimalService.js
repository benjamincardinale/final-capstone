import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {
    getAnimals() {
        return http.get('/')
    },

    postAnimal(animal) {
        return http.post('/add/pet', animal)
    },
    updateListing(animal, animalID) {
        return http.put('/placeholder/' + animalID , animal)
    }
    //insert update path in put('/placeholder/')

}