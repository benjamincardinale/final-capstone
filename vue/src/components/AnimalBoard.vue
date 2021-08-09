<template>
  <div class="cards">
    <div class="petCard" v-for="item in $store.state.animalCards" v-bind:key="item.id">
      <h2>{{item.name}}</h2>
      <img :src="item.imageUrl"
           :alt="item.imageDescription" />
      <div class="info-line">
        <p class="top-line">Gender: {{item.gender}}</p>
        <p class="top-line">Age: {{item.age}} Months</p>
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
      
    };
  },
  created() {
    this.retrieveAnimals();
    },
  computed: {
    
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
  flex-direction: column;
  flex-wrap: nowrap;
  margin-left: 15%;
  margin-right: 15%;
  margin-top: 2.5%;
}
.petCard {
    display: flex;
    background:linear-gradient( rgba(255,203,154,.5), rgba(216, 195, 165,.5), rgba(255,203,154,.5));
    opacity: 0.9;
    flex-direction: column;
    justify-content: space-around;
    border: 2px solid rgba(248, 203, 4, 0.753);
    border-radius: 15px;
    padding: 15px;
    text-align: center;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 2em;
}
h2 {
  margin-top: 2%;
  text-align: center;
  
}
.info-line {
  display: flex;
  margin-top: 2.5%;
  justify-content: space-around;
} 
.summary {
  display: flex;
  flex-direction: column;
  padding: 20px;
}

img {
  align-self: center;
  max-width: 50%;
  height: auto;
  padding: 20px;
  border: 2px solid black;
  border-radius: 30px;

}
@media (max-width: 1000px) {
  .cards {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 2.5% 15% 0% 20%;
    font-size: .75em;
  }
  
}


</style>