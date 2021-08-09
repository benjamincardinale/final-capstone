import axios from 'axios';

export default {

    addImage(image) {
        /*return axios({
            method: 'post',
            url: '/image/upload',
            data: image,
            headers: {
                'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        })*/
        const config = {headers: {'content-type': 'application/json'}};
        
        const payload = {'image': image};
        return axios.post("/image/upload", payload, config);


    }
}
