<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <div class="txt-boxes">
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>  
      <!--<router-link :to="{ name: 'register' }">Need an account?</router-link>-->
      <button type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$store.commit("SET_ROLE", response.data.user.authorities[0].name)
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#login {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center; 
  border: 2px solid black;
  border-radius: 12px;
  padding: 5px;
  padding-bottom: 30px; 
  margin-left: 20%;
  margin-right: 20%;
  margin-top: 5%;
  background-color: rgba(241, 138, 41, 0.418);
}
.txt-boxes {
  display: flex;
  flex-direction: row;
  max-width: 25%;
  justify-content: space-between;
  text-align: left;
  padding: 10px;
}


button {
  max-width: 200px;
  margin-top: 5%;
  
  font-size: 1em;
}
</style>
