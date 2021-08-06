import axios from 'axios';



export default {
    getAnimals() {
        return axios.get('/')
    },

    postAnimal(animal) {
        return axios.post('/add/pet', animal)
    },
    updateListing(animal, animalID) {
        return axios.put('/placeholder/' + animalID , animal)
    }
    //insert update path in put('/placeholder/')

}