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
    }

}