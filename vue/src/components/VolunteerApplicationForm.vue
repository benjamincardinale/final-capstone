<template>
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
#volunteer-form{
  display: flex;  
  max-width: 50%;
  
}
.text-boxes {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  
  
}
.buttons {
  display: flex;
  justify-content: space-between;
}

</style>