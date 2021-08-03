<template>
  <div id="volunteer-form">
    <form v-on:submit.prevent="submitForm">
      <label for="fname">First name:</label>
      <input
        type="text"
        id="fname"
        name="fname"
        v-model="application.firstname"
      />
      <label for="lname">Last name:</label>
      <input
        type="text"
        id="lname"
        name="lname"
        v-model="application.lastname"
      />
      <label for="email">Email address:</label>
      <input type="text" id="email" name="email" v-model="application.email" />
      <button class="btn btn-submit">Submit</button>
      <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>  

    </form>
  </div>
</template>

<script>
import volunteerService from '@/components/VolunteerApplicationForm'

export default {
    name: "volunteer-form",
    data() {
        return {
            application: {
                firstname: "",
                lastname: "",
                email: ""
            }
        };
    },
    methods: {
        submitForm() {
        const newApp = {
            firstname: this.application.firstname,
            lastname: this.application.lastname,
            email: this.application.email
            };
            //add the service call here
            volunteerService.apply(newApp)
            .then(response => {
                if (response.status === 201) {
                    alert("Thank you for your application!")
                    this.$router.push('/')
                } 
            })
            .catch(error => {
                alert("Error" + error.status)
            })
        },
        cancelForm() {
            this.$router.push('/')
        }

    }
}
</script>

<style>
</style>