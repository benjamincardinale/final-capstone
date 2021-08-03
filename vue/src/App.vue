<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <the-header/>
    <nav-panel/>
    <router-view />
    <the-footer/>
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";
import TheFooter from "@/components/TheFooter.vue";
import NavPanel from "@/components/NavPanel.vue"


export default {
  components: {
    TheHeader,
    TheFooter,
    NavPanel
  },
};
</script>

<style> /*
 #app {
   background: url("/assets/background_image.jpg");
 } */
</style>
