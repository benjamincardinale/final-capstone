<template>
  <div class="nav-panel">
    <div >
      <ul class="nav-links">
      <li><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>&nbsp;&nbsp;
      <li><router-link v-bind:to="{ name: 'about' }">About</router-link></li>&nbsp;&nbsp;
      <li><router-link id="volunteer-registration" v-bind:to="{ name: 'apply' }"
        >Volun. Reg.</router-link
      ></li>&nbsp;&nbsp;
      <li><router-link v-bind:to="{ name: 'logout' }">Logout</router-link
      ></li>&nbsp;&nbsp;
      <li><router-link v-bind:to="{ name: 'login' }">Login</router-link></li>
      </ul>
    </div>
    <!-- volunteer link-->
    <!-- add pets-->
  </div>
</template>

<script>
export default {
  name: "nav-panel",
};
</script>

<style>
.nav-links {
  border-radius: 35px;
  text-align: left;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-size: 31px;
  background: rgb(233, 128, 116);
  padding: 10px;
  margin: 5px;

}
.nav-panel {
  display: flex;
  flex-direction: column;
  position: fixed;
  width: 12vw;
  top: 0;
  left: 0;
  margin-bottom: -10px;
}
ul li{
  list-style-type: none;
  height: 10vh;
}

</style>