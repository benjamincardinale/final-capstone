<template>
  <div>
    <h1>About</h1>
    <about-page />
  </div>
</template>

<script>
import AboutPage from "../components/AboutPage.vue";

export default {
  components: { AboutPage },
  name: "about-view",
};
</script>

<style>
h1 {
    margin-top: -50px;
    margin-bottom: 80px;
}
</style>