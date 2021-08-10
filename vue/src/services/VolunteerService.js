import axios from 'axios'


export default {
    
    apply(application) {
        return axios.post('/apply', application)
    },
    getPending() {
        return axios.get('/admin/pending')
    },
   putApproved(arrayOfIds) {
       return axios.put('/admin/approve', arrayOfIds)
   },
   putDeclined(arrayOfIds) {
       return axios.put('/admin/decline', arrayOfIds)
   },
   getDirectory() {
       return axios.get('/user/directory')
       
   }, 
    
}
