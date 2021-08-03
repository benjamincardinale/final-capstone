import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {
    
    apply(application) {
        return http.post('/apply', application)
    },
    
}
