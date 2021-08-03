<template>
  <div class="nav-panel">
      <div class="nav-links">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'apply' }">Volunteer Registration</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }">Logut</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }">Login</router-link>
      
      </div>
      <!-- volunteer link-->
      <!-- add pets-->
  </div>
</template>

<script>


export default {
    name: "nav-panel"


}
</script>

<style>
.nav-links{
   background-color: rgba(41, 41, 41, 0.5);
  border-radius: 35px;
  text-align: center;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
 }

 
</style>