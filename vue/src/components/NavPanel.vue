<template>
  <div class="nav-panel">
    <div >
      <ul class="nav-links">
      <li><router-link v-bind:to="{ name: 'home' }" >Home</router-link></li><br>
      <li><router-link v-bind:to="{ name: 'about' }">About</router-link></li><br>
      <li><router-link id="volunteer-registration" v-bind:to="{ name: 'apply' }"
        >Volunteers Register Here</router-link
      ></li><br><br>
      <li><router-link v-bind:to="{ name: 'logout' }">Logout</router-link
      ></li><br>
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

<style scoped>
.nav-links {
  border-radius: 35px;
  text-align: center;
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
  width: 14vw;
  margin: 10px 0 -10px 10px
}
ul li{
  list-style-type: none;
  height: 10vh;
}
.router-link-exact-active {
  color: white;
}
@media (max-width: 1000px) {
  .nav-panel {
    display: flex;
    flex-direction: row;
    position: absolute;
    margin: 7.5% 0 0 0;
  }
  .nav-links {
    font-size: 1.25em;
  }
}
</style>