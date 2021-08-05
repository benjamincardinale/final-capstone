<template>
  <div class="nav-panel">
      <div class="nav-links">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'about' }">About</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'apply' }">Volunteer Registration</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>&nbsp;|&nbsp;
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
  border-radius: 35px;
  text-align: center;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  margin-bottom: 100px;
  font-size: 2em;
 }

 
</style>