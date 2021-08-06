<template>
  <div>
      <input type="file" @change="onFileSelected">
      <button @click="onUpload">Upload</button>
  </div>
</template>

<script>
import imageService from '../services/ImageService.js'

export default {
    name: 'image-submission-form',
    data () {
        return {
           selectedFile: null 
        }
    },
    methods: {
        onFileSelected(event) {
            this.selectedFile = event.target.files[0];
        },
        onUpload() {
            const formData = new FormData();
            formData.append('image', this.selectedFile, this.selectedFile.name)
            imageService.postImage(formData)
            .then(response => {
                console.log(response)
                if (response.status == 200) {
                    // Maybe right here we'll want to use response.data.url to get the url and save it
                    alert("Image Upload Successful!")
                }
            })
            .catch(error => {
                alert("Error: " + error.status)
            })
        }
    }
}
</script>

<style>

</style>