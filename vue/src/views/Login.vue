<template>
  <div>
    <div class=registration>
      <router-link
              id="volunteer-registration"
              v-bind:to="{ name: 'apply' }"
              v-if="$store.state.role == ''"
              >Don't have an account? <br> Start your volunteer aplication here!
      </router-link>
    </div>           
    <div id="login" class="text-center">
      <form class="form-signin" @submit.prevent="login">
        <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
        <div
          class="alert alert-danger"
          role="alert"
          v-if="invalidCredentials"
        >Invalid username or password!</div><br>
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
          <label for="password" class="sr-only" v-if="!$store.state.user.newUser">Password</label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            v-if="!$store.state.user.newUser"
            required
          />
          
        </div>

        <div class="change-password" v-if="$store.state.user.newUser">
            <label for="new-password">Please change your password</label>
            <input
              type="password"
              id="new-password"
              name="new-password"
              placeholder="New Password"
              v-model="passwordChange.newPassword"
              required
              />
              <button v-on:click="changePassword">Change Password</button> 
          </div> <br><br> 
        <div
          class="alert alert-success"
          role="alert"
          v-if="changed"
        >Click Sign In to continue
        </div>
          
        <button id=sign-in type="submit">Sign in</button>
      </form>
      
      
    </div>
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
      invalidCredentials: false,
      passwordChange: {
        newPassword: '',
      },
      changed: false
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
            if(!this.$store.state.user.newUser) {
              this.$router.push('/');
            }
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            if(!this.$store.state.user.newUser)
            this.invalidCredentials = true;
          }
        });
    },
    changePassword() {
      authService.changePassword(this.passwordChange)
      .then((response => {
        if(response.status === 200) {
          alert('Your password has been changed!')
          this.changed = true;
          this.user.password = this.passwordChange.newPassword
        }
      })).catch((error) => {
        alert('ERROR: '+ error.message)
      })
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
  margin-left: 25%;
  margin-right: 25%;
  margin-top: 2.5%;
  background-color: rgba(241, 138, 41, 0.418);
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  font-size: 14pt;
}
.txt-boxes {
  display: flex;
  flex-direction: column;
  width: 25vw;
  height: 5vh;
  justify-content: center;
  align-items: center;
  text-align: left;
  padding: 10px;
}
label {
  margin: 5px;
}
.change-password {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 25vw;
  margin: 2.5% 2% -2.5% 2.5%;
  font-size: 14pt;

}
.change-password button {
  margin: 2vh;
}
input {
  height: 20px;
}
.alert {
  margin: 0 0 5% 0;
  
}
#sign-in {
  font-size: 14pt;
  margin-bottom: 5%;
}

button {
  max-width: 200px;
  font-size: .85em;
}
.registration {
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  font-size: 1.25em;
  margin: 2vh 0 0 0;
  
}
#volunteer-registration {
  text-decoration: none;
}

@media (max-width: 1001px){
    .txt-boxes {
  }
}
@media (max-width: 1000px) {
  .txt-boxes {

    max-width: 50vw;
    padding: 5px;
  }
  #login {
    margin-top: 10%;
    margin-bottom: 10%;
  }
}
@media (max-width: 700px){
  
  #login {
    margin-left: 4.8em;
    margin-right: 1.1em;
  }
  .txt-boxes{
  margin-left: 2.1em;
}
}



</style>
