<template>
  <div class="update">
    <div class="id-section">
      <div class="table-of-pets">
        <table>
          <tr>
            <th>Pet ID</th>
            <th>Name</th>
          </tr>
          <tr v-for="item in $store.state.animalCards" v-bind:key="item.id">
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

    <div class="update-form" v-if="targetAnimal.id > 0">
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
          <label for="adopted">Adopted?</label>
          <input
            type="checkbox"
            id="adopted"
            name="adopted"
            v-model="adopted"
          />
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
      <div class="adopted-form" v-if="adopted == true" v-on:submit.prevent="adoptedAnimal">
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
            v-model="adoptionInfo.date"
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
  </div>
</template>

<script>
import animalService from "../services/AnimalService.js";

export default {
  name: "update-pets",
  data() {
    return {
      adopted: false,
      targetAnimal: {
        id: "",
        name: "",
        gender: "",
        age: "",
        type: "",
        description: "",
        imageUrl: "",
      },
      adoptionInfo: {
        name: "",
        date: "",
        animalId: "",
      },
      selectedFile: null,
      fileAsB64: null,
      
    };
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
            alert("Submission successful");
            this.resetIdForm();
            this.retrieveAnimals();
          }
        })
        .catch((error) => {
          alert("Error: try again: " + error.message);
          this.resetIdForm();
          this.retrieveAnimals();
        });
    },
    retrieveAnimals() {
      animalService.getAnimals().then((response) => {
        this.$store.commit("SET_CARDS", response.data);
      });
    },
    adoptedAnimal() {
      this.adoptionInfo.animalId = this.targetAnimal.id;
      //add adoption post here and in animalService
      //then success
      //catch error
    },
    resetAdoptionForm() {
      this.adoptionInfo = {
        name: "",
        date: "",
        animalId: this.targetAnimal.id,
      };
      this.adopted = false;
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
        console.log("Error: ", error);
      };
    },
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
      this.getBase64(this.selectedFile);
    },
    submitAdoptionInfo() {
      this.adoptionInfo.animalId = this.targetAnimal.id
      //endpoint with adoptionInfo
      //then
      //catch
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
</style>