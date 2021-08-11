import axios from 'axios';

export default {

    addImage(image) {
        
        const config = {headers: {'content-type': 'application/json'}};
        const payload = {'image': image};
        return axios.post("/image/upload", payload, config);

    }
}
