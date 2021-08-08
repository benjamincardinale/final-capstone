import axios from 'axios';

export default {

    addImage(image) {
        return axios.post('/image/upload', image);
    }
}
