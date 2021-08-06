<template>
  <div class="home">
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

<style>
div h1 {
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

</style>
