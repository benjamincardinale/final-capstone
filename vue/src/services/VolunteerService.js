import axios from 'axios'


export default {
    
    apply(application) {
        return axios.post('/apply', application)
    },
    
}
