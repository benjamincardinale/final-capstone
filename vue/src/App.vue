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
    <router-view />
    <the-footer/>
  </div>
</template>

<script>
import TheHeader from "/@/components/TheHeader";
import TheFooter from "/@/components/TheFooter";

export default {
  components: {
    TheHeader,
    TheFooter,
  },
};
</script>
