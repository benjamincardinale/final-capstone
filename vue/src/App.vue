<template>
  <div id="app">
    <div id="nav">
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link>
    </div>
    <the-header />
    <nav-panel />
    <hidden-functions/>
    <router-view class="routerView" />
    <the-footer />
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";
import TheFooter from "@/components/TheFooter.vue";
import NavPanel from "@/components/NavPanel.vue";
import HiddenFunctions from './components/HiddenFunctions.vue';

export default {
  components: {
    TheHeader,
    TheFooter,
    NavPanel,
    HiddenFunctions,
  },
};
</script>

<style>
body {
  background-image: url("assets/background_image.jpg");
  background-attachment: fixed;
}
.routerView{
  text-align: center;
}
</style>
