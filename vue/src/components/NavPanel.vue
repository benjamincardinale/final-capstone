<template>
  <div class="nav-panel">
    <div>
      <ul class="nav-links">
        <br />
        
        <li><router-link v-bind:to="{ name: 'home' }"><span class="linktext">Home</span></router-link></li>
        <br />
        <li><router-link v-bind:to="{ name: 'about' }">About</router-link></li>
        <br />
        <li>
          <router-link
            id="volunteer-registration"
            v-bind:to="{ name: 'apply' }"
            v-if="$store.state.role == ''"
            >Volunteers Register Here</router-link
          >
          <router-link
            id="contact-list"
            v-else
            v-bind:to="{ name: 'contact-list-view' }"
            >Contact List</router-link
          >
        </li>
        <br />
        <br />
        <li>
          <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
        </li>
        <br />
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
  border-radius: 25px;
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
  margin: 9.5vh 0 5vh -5px;
}
ul li {
  list-style-type: none;
  height: 8vh;
}
.router-link-exact-active {
  color: white;
}
.nav-panel a {
  text-decoration: none;
}
.nav-panel a:hover {
  text-decoration: underline;
}
@media (max-width: 1100px) {
  .nav-links{
    font-size: 1.3em;
  }
}
@media (max-width: 1000px) {
  .nav-panel {
    display: flex;
    flex-direction: row;
    position: fixed;
    margin: 6% 0 0 0;
  }
  .nav-links {
    font-size: 1.25em;
  }
}
@media (max-width: 700px) {
  .nav-panel {
    display: flex;
    flex-direction: row;
    position: fixed;
    margin: 7.5% 0 0 -1%;
  }
  .nav-links {
    font-size: 0.75em;
  }
}
</style>