<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <animal-board/>
  </div>
</template>

<script>
import AnimalBoard from '../components/AnimalBoard.vue';
export default {
  components: { AnimalBoard },
  name: "home"
};
</script>
