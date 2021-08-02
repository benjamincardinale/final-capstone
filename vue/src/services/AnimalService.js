import axios from 'axios';

const http = axios.create({
    baseURL: tbd
});
export default {
    getAnimals() {
        return http.get('/')
    },
    
}