<template>
  <div class="app-card">
    <h1 class="title">Volunteer Application</h1>
    <div id="volunteer-form">
      <form v-on:submit.prevent="submitForm">
        <div class="text-boxes">
          <label for="fname">First name:</label>
          <input
            type="text"
            id="fname"
            name="fname"
            v-model="application.firstName"
          /><br>
          <label for="lname">Last name:</label>
          <input
            type="text"
            id="lname"
            name="lname"
            v-model="application.lastName"
          /><br>
          <label for="email">Email address:</label>
          <input type="text" id="email" name="email" v-model="application.emailAddress" /><br>
        </div>  
        <div class="buttons">
          <button class="btn btn-submit">Submit</button>
          <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>  
        </div>
      </form>
    </div>
  </div>  
</template>

<script>
import volunteerService from '@/services/VolunteerService.js'

export default {
    name: "volunteer-application-form",
    data() {
        return {
            application: {
                firstName: "",
                lastName: "",
                emailAddress: ""
            }
        };
    },
    methods: {
        submitForm() {
        const newApp = {
            firstName: this.application.firstName,
            lastName: this.application.lastName,
            emailAddress: this.application.emailAddress
            };
            //add the service call here
            volunteerService.apply(newApp)
            .then(response => {
                if (response.status === 200) {
                    alert("Thank you for your application!")
                    this.$router.push('/')
                } 
            })
            .catch(error => {
                alert("Error: " + error.status)
            })
        },
        cancelForm() {
            this.$router.push('/')
        }

    }
}
</script>

<style>
.app-card {
  display: flex;
  flex-direction: column;
}

.title {
  text-align: center;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  font-size: 3em;
  padding: 40px;
}



#volunteer-form{
  display: flex;  
  flex-direction: column;
  max-width: 50%;
  background-color: rgba(255, 255, 255, 0.356);
  border: 2px solid black;
  padding: 35px;
  border-radius: 45px;
  margin-right: 25%;
  margin-left: 25%;
  margin-top: 5%;
  margin-bottom: 10%;
}
.text-boxes {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  font-size: 1.5em;
  
  
}
.buttons {
  display: flex;
  justify-content: space-between;
}
button {
  width: 40%;
  font-size: 1.5em;
}
button:hover {
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}

</style>