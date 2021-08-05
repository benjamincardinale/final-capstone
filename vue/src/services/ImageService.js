import axios from 'axios';

const http = axios.create({
    baseURL: "https://api.imgbb.com/1/upload"
})

export default {
    postImage(image) {
        let config = {
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'multipart/form-data'
            },
            body: image
        }
        return http.post('?key=cf122104a5dfbf471b70ae94aea0eacd', image, config)
    }
}
