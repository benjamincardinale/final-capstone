<template>
  <div class="main">
    <div class="submission-form">
      <form v-on:submit.prevent="submitForm">
        <h2>Create New Pet</h2>
        <br />
        <div class="gender-radio">
          <label for="gender" id="gender" name="gender">Gender: </label>
          <input
            type="radio"
            id="gender-m"
            name="gender"
            value="M"
            v-model="newAnimal.gender"
          />
          <label for="gender-m"> M</label>
          <input
            type="radio"
            id="gender-f"
            name="gender"
            value="F"
            v-model="newAnimal.gender"
          />
          <label for="gender-f"> F</label>
        </div>
        <label for="petName">Pet Name: </label>
        <input
          type="text"
          id="petName"
          name="petName"
          v-model="newAnimal.name"
        />
        <label for="age">Pet's age in months: </label>
        <input type="number" id="age" name="age" v-model="newAnimal.age" />
        <label for="type-of-animal">What type of animal? </label>
        <input
          type="text"
          id="type-of-animal"
          name="type-of-animal"
          v-model="newAnimal.species"
        />
        <label for="text" id="description" name="description"
          >Brief Description:
        </label>
        <input
          type="text"
          id="description"
          name="description"
          v-model="newAnimal.description"
        />
        <label for="image-url">Upload Image: </label>
        <input type="file" @change="onFileChanged" />
        <button @click.prevent="onUpload">Upload!</button>

        <div class="buttons">
          <button class="btn btn-submit">SUBMIT</button>
          <button
            class="btn btn-cancel"
            type="cancel"
            v-on:click.prevent="cancelForm"
          >
            CANCEL
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import animalService from "../services/AnimalService.js";
import imageService from "../services/ImageService.js";
//import axios from 'axios';

export default {
  name: "pet-submission-form",
  data() {
    return {
      newAnimal: {
        name: "",
        gender: "",
        age: "",
        species: "",
        description: "",
        imageUrl: "",
      },
      selectedFile: null,
      fileAsB64: null
    };
  },
  methods: {
    cancelForm() {
      this.$router.push("/");
      this.newAnimal = {
        name: "",
        gender: "",
        age: "",
        species: "",
        description: "",
        imageUrl: "",
      };
    },
    submitForm() {
      const animalToAdd = {
        name: this.newAnimal.name,
        gender: this.newAnimal.gender,
        age: this.newAnimal.age,
        species: this.newAnimal.species,
        description: this.newAnimal.description,
        imageUrl: this.newAnimal.imageUrl,
      };
      animalService
        .postAnimal(animalToAdd)
        .then((response) => {
          if (response.status === 200) {
            alert("Submission successful");
            this.newAnimal = {
              name: "",
              gender: "",
              age: "",
              species: "",
              description: "",
              imageUrl: "",
            };
          }
        })
        .catch((error) => {
          alert("Error: try again : " + error.message);
        });
    },
    getBase64(file) {
      let self = this;
      console.log(self);
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function () {
        console.log(reader.result);
        self.fileAsB64 = reader.result.substring(reader.result.indexOf(',')+1);
      };
      reader.onerror = function (error) {
        console.log('Error: ', error);
      };
    },
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
      this.getBase64(this.selectedFile);
    },
    onUpload() {
      /*const formData = new FormData();
      formData.append("myFile", this.selectedFile);
      console.log(formData);
      axios.post("http://localhost:8080/image/upload", this.selectedFile {'Content-Type': 'multipart/form-data'})
      .then(res => {
          console.log(res); 
      })*/
      //let imageAsB64 = this.getBase64(this.selectedFile);
      //console.log(imageAsB64);
      imageService.addImage(this.fileAsB64);
  },
}
}
</script>

<style scoped>
.main {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 50vh;
}
h2 {
  text-align: center;
  font-size: 2em;
}
.submission-form form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  font-size: 14pt;
  text-align: left;
  width: 500px;
  padding: 40px;
  border: 2px solid rgba(248, 203, 4, 0.753);
  background-color: rgba(250, 201, 154, 0.479);
}
.submission-form input {
  margin-top: 5px;
  margin-bottom: 5px;
}
.submission-form .gender-radio {
  text-align: center;
}
.buttons {
  margin-top: 10px;
}
</style>