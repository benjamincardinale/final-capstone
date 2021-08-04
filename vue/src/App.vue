<template>
  <div id="app">
    <div id="nav">
    
    </div>
    <the-header />
    <nav-panel />
    <router-view class="routerView" />
    <the-footer />
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";
import TheFooter from "@/components/TheFooter.vue";
import NavPanel from "@/components/NavPanel.vue";

export default {
  components: {
    TheHeader,
    TheFooter,
    NavPanel,
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
