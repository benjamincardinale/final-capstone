import axios from 'axios';

export default {
    getAnimals() {
        return axios.get('/')
    },

}