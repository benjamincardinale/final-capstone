import axios from 'axios'


export default {
    
    apply(application) {
        return axios.post('/apply', application)
    },
    getPending() {
        return axios.get('/admin/pending')
    }
    
}
