<template>
  <div class="cards">
    <div class="petCard" v-for="animal in animalCards" v-bind:key="animal.id">
      <h2>{{animal.name}}}</h2>
      <img src="placeholder.url" alt="description of animal" />
      <div class="description">
        <p class="top-line">Gender: {{animal.gender}}</p>
        <p class="top-line">Age: {{animal.age}}</p>
        <p class="mid-line">{{animal.species}}</p>
        <p class="summary">{{animal.description}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import animalService from "@/services/AnimalServices.js";

export default {
  data() {
    return {
      animalCards: [],
    };
  },
  created() {
    animalService.getAnimals().then((response) => {
      this.animalCards = response.data;
    });
  },
};
</script>

<style scoped>
.cards {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
}
.petCard {
    background-color: darkolivegreen;
    border-radius: 10px;
    flex: 1;
    text-align: center;
}
.top-line {
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-wrap: nowrap;
}
.mid-line {
  justify-content: center;
  align-items: center;
  flex-grow: 2; /*not sure if this will space as intended yet */
}
.summary {
  display: flex;
  justify-content: center;
}
</style>