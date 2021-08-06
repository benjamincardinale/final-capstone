<template>
  <div id="app">
    <nav-panel />
    <the-header />
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
  /*background-image: url("assets/background_image.jpg");*/
  background: rgb(234, 231, 220);
  background-attachment: fixed;
 /*display:grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
   "nav-panel the-header the-header the-header"
   "nav-panel hidden-functions router-view router-view"
   "nav-panel router-view router-view router-view "
   "nav-panel the-footer the-footer the-footer";*/
}
.routerView{
  text-align: center;
}

the-header {
  grid-area: the-header;
}
nav-panel{
  grid-area: nav-panel;
}
hidden-functions{
  grid-area: hidden-functions;
}
router-view{
  grid-area: router-view;
}
footer{
  grid-area: footer;
}
</style>
