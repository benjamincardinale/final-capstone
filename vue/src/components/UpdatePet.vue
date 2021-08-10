<template>
  <div class="update">

    <div class="first-choice">
        <p>Would you like to update a Pet's information, or mark a pet as adopted?</p>
        <form>
          <input type="radio" id="info-change" name="first-choice" value="true" v-model="infoChange">
          <label for="info-change"> Change Info </label>
          <input type="radio" id="mark-adopted" name="first-choice" value="true" v-model="markAdopted">
          <label for="mark-adopted"> Mark Adopted </label>

        </form>
      </div>

    <div class="id-section" v-if="markAdopted || infoChange">
      <div class="table-of-pets">
        <table>
          <tr>
            <th>Pet ID</th>
            <th>Name</th>
          </tr>
          <tr v-for="item in filteredAnimalsByAdoption" v-bind:key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
          </tr>
        </table>
      </div>

      <div class="id-selector">
        <form v-on:submit.prevent="setTargetAnimalById">
          <label for="target-id">ID of pet to update: </label>
          <input
            type="text"
            autocomplete="off"
            name="target-id"
            id="target-id"
            v-model.lazy="targetAnimal.id"
          />
          <button class="btn btn-submit">SUBMIT</button>
          <button class="btn btn-reset" v-on:click="resetIdForm">RESET</button>
        </form>
      </div>
    </div>
    <div class="confetti button">
      <form v-if="confettiActive">
          <button v-on:click="stopConfetti">Stop Celebration</button>
      </form>
    </div>
    <div class="update-form" v-if="targetAnimal.id > 0 && infoChange">
      <form v-on:submit.prevent="submitForm">
        <label for="petName">Pet Name: </label>
        <input
          type="text"
          id="petName"
          name="petName"
          v-model="targetAnimal.name"
        />
        <div class="gender-radio">
          <label for="gender" id="gender" name="gender">Gender: </label>
          <input
            type="radio"
            id="gender-m"
            name="gender"
            value="M"
            v-model="targetAnimal.gender"
          />
          <label for="gender-m"> M</label>
          <input
            type="radio"
            id="gender-f"
            name="gender"
            value="F"
            v-model="targetAnimal.gender"
          />
          <label for="gender-f"> F</label>
          <br />
          
        </div>
        <label for="age">Pet's age in months: </label>
        <input type="number" id="age" name="age" v-model="targetAnimal.age" />
        <label for="type-of-animal">What type of animal? </label>
        <input
          type="text"
          id="type-of-animal"
          name="type-of-animal"
          v-model="targetAnimal.species"
        />
        <br />
        <label for="description">Brief Description: </label>
        <textarea
          id="description"
          name="description"
          rows="4"
          cols="50"
          v-model="targetAnimal.description"
        ></textarea>
        <br />
        <label for="image-url">Upload Image: </label>
        <input type="file" @change="onFileChanged" />

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
    <div class="adopted-form" v-if="targetAnimal.id > 0 && markAdopted" v-on:submit.prevent="adoptedAnimal">
        <form class="adoption">
          <label for="adopter">Name of Adopter: </label>
          <input
            type="text"
            id="adopter"
            name="adopter"
            v-model="adoptionInfo.name"
          />
          <label for="adoption-date">Adoption Date: </label>
          <input
            type="date"
            id="adoption-date"
            name="adoption-date"
            v-model="adoptionInfo.adoptionDate"
          />
          <div class="adoption-btns">
            <button class="btn btn-submit">SUBMIT</button>
            <button
              class="btn btn-cancel"
              type="cancel"
              v-on:click="resetAdoptionForm"
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
import Vue from 'vue'
import VueConfetti from 'vue-confetti';
Vue.use(VueConfetti)

export default {
  name: "update-pets",
  data() {
    return {
      targetAnimal: {
        id: "",
        name: "",
        gender: "",
        age: "",
        species: "",
        description: "",
        imageUrl: "",

      },
      adoptionInfo: {
        name: "",
        adoptionDate: "",
        petId: "",
      },
      confettiActive: false,
      selectedFile: null,
      fileAsB64: null,
      
      markAdopted: false,
      infoChange: false,

    };
  },
  computed: {
    filteredAnimalsByAdoption () {
      let filteredArray = this.$store.state.animalCards.filter((item) => {
        if(!item.adopted) {
          return true
        }
      })
      return filteredArray;
    }
  },
  methods: {
    setTargetAnimalById() {
      const animal = this.$store.state.animalCards.find((item) => {
        if (item.id == this.targetAnimal.id) {
          return true;
        }
      });
      this.targetAnimal.id = animal.id;
      this.targetAnimal.name = animal.name;
      this.targetAnimal.gender = animal.gender;
      this.targetAnimal.age = animal.age;
      this.targetAnimal.species = animal.species;
      this.targetAnimal.description = animal.description;
      this.targetAnimal.imageUrl = animal.imageUrl;
      
    },
    resetIdForm() {
      this.targetAnimal = {
        id: "",
        name: "",
        gender: "",
        age: "",
        type: "",
        description: "",
        imageUrl: this.fileAsB64,        
      };
      this.markAdopted = false;
      this.infoChange = false;
    },
    submitForm() {
      const animalToadd = {
        name: this.targetAnimal.name,
        gender: this.targetAnimal.gender,
        age: this.targetAnimal.age,
        species: this.targetAnimal.species,
        description: this.targetAnimal.description,
        image: this.fileAsB64,
      };
      animalService
        .updateListing(animalToadd, this.targetAnimal.id)
        .then((response) => {
          if (response.status === 200) {
            this.resetIdForm();
            this.resetAdoptionForm();
            alert("Submission successful");
            
          }
        })
        .catch((error) => {
          this.resetIdForm();
          this.resetAdoptionForm();
          alert("Error: try again: " + error.message);
          
          
        });
    },
    retrieveAnimals() {
      animalService.getAnimals().then((response) => {
        this.$store.commit("SET_CARDS", response.data);
      });
    },
    adoptedAnimal() {
      this.adoptionInfo.petId = this.targetAnimal.id;
      animalService.createAdoption(this.adoptionInfo)
      .then(response => {
        if(response.status === 200) {
          this.resetAdoptionForm();
          this.resetIdForm();
          alert('adoption successfully submited')
          this.startConfetti();
          
        }
      })
      .catch(error => {
        this.resetAdoptionForm();
        this.resetIdForm();
        alert('Error: ' + error.message)
      })
    },
    resetAdoptionForm() {
      this.adoptionInfo = {
        name: "",
        date: "",
        animalId: "",
      };
    },
    getBase64(file) {
      let self = this;
      console.log(self);
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function () {
        console.log(reader.result);
        self.fileAsB64 = reader.result.substring(
          reader.result.indexOf(",") + 1
        );
      };
      reader.onerror = function (error) {
        console.log("Error: "+ error.message);
      };
    },
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
      this.getBase64(this.selectedFile);
    },
    startConfetti() {
      this.$confetti.start();
      this.confettiActive = true;
    },
    stopConfetti() {
      this.$confetti.stop();
      this.confettiActive = false;
      this.$router.push('/');
    }
  },
};
</script>

<style scoped>
* {
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  font-size: 14pt;
}
.update-form form {
  display: flex;
  flex-direction: column;
  background-color: rgba(250, 201, 154, 0.479);
  border: 3px solid rgba(248, 203, 4, 0.753);
  text-align: left;
  margin: 2.5% 25% 0 25%;
  padding: 0px 10px 0px 10px;
}
.update-form button {
  margin: 5px 0 5px 0;
}
.update-form .gender-radio {
  text-align: center;
}
.update-form input {
  margin: 2px 0 10px 0;
}

.id-section {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  margin: 5% 25% 0 25%;
}
.id-section form {
  display: flex;
  align-items: center;
  font-size: 0.85em;
  background-color: rgba(250, 201, 154, 0.479);
  border: 1px solid rgba(248, 203, 4, 0.753);
}

.table-of-pets {
  padding: 10px;
  background-color: rgba(250, 201, 154, 0.479);
  border: 2px solid rgba(248, 203, 4, 0.753);
}
.table-of-pets th {
  text-decoration: underline;
  text-decoration-color: black;
}

.id-selector {
  display: flex;
  flex-direction: column;
  margin-top: 2.5%;
  padding: 15px;
}
.id-selector button {
  max-height: 31px;
  max-width: 75px;
}
.id-selector input {
  max-height: 25px;
  max-width: 75px;
}
.adopted-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.adoption-btns {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
.adopted-form {
  display: flex;
  align-items: center;
  background-color: rgba(250, 201, 154, 0.479);
  border: 3px solid rgba(248, 203, 4, 0.753);
  text-align: center;
  margin: 5% 35% 5% 35%;  
}
.adopted-form form {
  display: flex;
  flex-direction: column;
  width: 80%;
}
.adopted-form button {
  margin-top: 2vh;
}
.first-choice {
  margin: 2.5% 0 5% 0;
}
</style>