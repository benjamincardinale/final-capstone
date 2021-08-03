<template>
  <div class="cards">
    <div class="petCard" v-for="item in this.$store.state.animalCards" v-bind:key="item.id">
      <h2>{{item.name}}</h2>
      <img v-bind:src="item.imageURL" v-bind:alt="item.image_description" />
      <div class="info-line">
        <p class="top-line">Gender: {{item.gender}}</p>
        <p class="top-line">Age: {{item.age}}</p>
        <p class="mid-line">Type: {{item.species}}</p>
      </div>
      <div class="summary">  
        <h4 class="summary-title">About {{item.name}}:</h4>
        <p class="summary-content">{{item.description}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import animalService from "@/services/AnimalService.js";

export default {
  data() {
    return {
      currentAnimalId: {
        id: ""
      }
    
    };
  },
  created() {
    this.retrieveAnimals();
    },
  computed: {
    imageSelector(animalID) {
      const targetAnimal = this.$store.state.animalCards.filter((item) => {
          item.id = animalID;
      });
      return targetAnimal.imgUrl;
    }


  },    
  
  methods: {
    retrieveAnimals() {
      animalService.getAnimals().then(response => {
        this.$store.commit("SET_CARDS", response.data);
      })
    }
  }
};
</script>

<style scoped>

.cards {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  flex-wrap: nowrap;
  margin: 10px;
}
.petCard {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    border: 2px solid tan;
    border-radius: 15px;
    padding: 15px;
    text-align: left;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}
h2 {
  text-align: center;
  
}
.info-line {
  display: flex;
  justify-content: space-around;
} 
.summary {
  display: flex;
  flex-direction: column;
  padding: 20px;
}
/*
img {

}
*/

</style>