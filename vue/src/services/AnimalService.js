import axios from 'axios';



export default {
    getAnimals() {
        return axios.get('/')
    },

    postAnimal(animal) {
        return axios.post('/pet/add', animal)
    },
    updateListing(animal, animalID) {
        return axios.put(`/pet/update/${animalID}` , animal)
    }
    

}