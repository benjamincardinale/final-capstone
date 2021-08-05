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
          <br>
          <label for="adopted">Adopted?</label>
          <input type="checkbox" id="adopted" name="adopted">

        </div>
        <label for="age">Pet's age in months: </label>
        <input type="number" id="age" name="age" v-model="targetAnimal.age" />
        <label for="type-of-animal">What type of animal? </label>
        <input
          type="text"
          id="type-of-animal"
          name="type-of-animal"
          v-model="targetAnimal.species"
        /> <br>
        <label for="description">Brief Description: </label>
        <textarea
          id="description"
          name="description"
          rows="4"
          cols="50"
          v-model="targetAnimal.description"
        ></textarea> <br>
        <label for="image-url">Image Url: </label>
        <input
          type="text"
          name="image-url"
          id="image-url"
          v-model="targetAnimal.imageUrl"
        />

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
export default {
  name: "update-pets",
  data() {
    return {
      targetAnimal: {
        id: "",
        name: "",
        gender: "",
        age: "",
        type: "",
        description: "",
        imageUrl: "",
      },
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
            imageUrl: "",
        }
    }
  },
};
</script>

<style scoped>

* {
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
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
  margin: 5% 25% 10% 25%;

}
.id-section form {
    display:flex;
    align-items: center;
    font-size: .85em;
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
</style>