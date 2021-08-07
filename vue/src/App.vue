<template>
  <div id="app">
   
   <the-header class="header" />
   <nav-panel class="nav" />
   <hidden-functions class="hidFunc" />
   <router-view class="routerView" />
   <the-footer class="footer" />
   
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";
import TheFooter from "@/components/TheFooter.vue";
import NavPanel from "@/components/NavPanel.vue";
import HiddenFunctions from "@/components/HiddenFunctions.vue";

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
  /*background-image: url("assets/background_image.jpg");*/
 background: hsla(47, 25%, 89%, 0.75);
  min-height: 100vh;
  display: flex;
  flex-direction: column;
 /*display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "header header header header"
    "nav hidFunc routerView routerView"
    "nav routerView routerView routerView"
    "footer footer footer footer";*/
}
.routerView {
  text-align: center;
 
}



</style>
