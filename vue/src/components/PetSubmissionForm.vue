<template>
  <div class="submission-form">
      <form v-on:submit.prevent="submitForm">
          <label for="petName">Pet Name: </label>
          <input type="text" id="petName" name="petName" v-model="newAnimal.name"><br>
          <div class="gender-radio">
            <label for="gender" id="gender" name="gender">Gender: </label>
            <input type="radio" id="gender-m" name="gender" value="M" v-model="newAnimal.gender">
            <label for="gender-m"> M</label>
            <input type="radio" id="gender-f" name="gender" value="F" v-model="newAnimal.gender">
            <label for="gender-f"> F</label>
          </div>
          <label for="age">Pet's age in months: </label>
          <input type="number" id="age" name="age" v-model="newAnimal.age">
          <label for="type-of-animal">What type of animal? </label>
          <input type="text" id="type-of-animal" name="type-of-animal" v-model="newAnimal.type">
          <label for="text" id="description" name="description" >Brief Description: </label>
          <input type="text" id="description" name="description" v-model="newAnimal.description">
          <label for="image-url">Image Url: </label>
          <input type="text" name="image-url" id="image-url" v-model="newAnimal.imageUrl">

          <div class="buttons">
              <button class="btn btn-submit">SUBMIT</button>
              <button class="btn btn-cancel" type="cancel" v-on:click.prevent="cancelForm">CANCEL</button>
          </div>
          <!-- submit btn, reset btn, cancel btn, -->
      </form>
  </div>
</template>

<script>
import animalService from '../services/AnimalService.js'

export default {
    name: "pet-submission-form",
    data() {
        return {
            newAnimal: {
                name: "",
                gender: "",
                age: "",
                type: "",
                description: "",
                imageUrl: ""

            }
        };
    },
    methods: {
        cancelForm() {
            this.$router.push('/')
        },
        submitForm() {
            const animalToAdd = {
                name: this.newAnimal.name,
                gender: this.newAnimal.gender,
                age: this.newAnimal.age,
                type: this.newAnimal.type,
                description: this.newAnimal.description,
                imageUrl: this.newAnimal.imageUrl
            }
            animalService.postAnimal(animalToAdd)
            .then(response => {
                if (response.status === 200) {
                    alert("Submission successful")
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