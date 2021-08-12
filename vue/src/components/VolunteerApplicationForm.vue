<template>
  <div class="app-card">
    <h1 class="title">Volunteer Application</h1>
    <div class="volunteer-form">
      <form v-on:submit.prevent="submitForm">
        <div class="text-boxes">
          <label for="userName">Username: </label>
          <input
            type="text"
            id="userName"
            name="userName"
            v-model="application.userName"
          /><br />
          <label for="fname">First name:</label>
          <input
            type="text"
            id="fname"
            name="fname"
            v-model="application.firstName"
          /><br />
          <label for="lname">Last name:</label>
          <input
            type="text"
            id="lname"
            name="lname"
            v-model="application.lastName"
          /><br />
          <label for="email">Email address:</label>
          <input
            type="text"
            id="email"
            name="email"
            v-model="application.emailAddress"
          /><br />
          <div class="yes-no-cert , radio-btns">
            <label for="cert-box"
              >Do you have any pet care certifications?
            </label>
            <input
              type="radio"
              id="cert-box-yes"
              name="cert-box"
              value="true"
              v-model="application.hasCertifications"
            />
            <label for="cert-box-yes">Yes</label>
            <input
              type="radio"
              id="cert-box-no"
              name="cert-box"
              value="false"
              v-model="application.hasCertifications"
            />
            <label for="cert-box-no">No</label>
          </div>
          <br />
          <div class="radio-btns">
            <label for="adopted-before"
              >Have you adopted a pet from a shelter before?</label
            >
            <input
              type="radio"
              id="adopted-before-yes"
              name="adopted-before"
              value="true"
              v-model="application.adoptedBefore"
            />
            <label for="adopted-before-yes">Yes</label>
            <input
              type="radio"
              id="adopted-before-no"
              name="adopted-before"
              value="false"
              v-model="application.adoptedBefore"
            />
            <label for="adopted-before-no">No</label>
          </div>
          <br />
        </div>
        <div class="buttons">
          <button class="btn btn-submit">Submit</button>
          <button
            class="btn btn-cancel"
            v-on:click.prevent="cancelForm"
            type="cancel"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import volunteerService from "@/services/VolunteerService.js";

export default {
  name: "volunteer-application-form",
  data() {
    return {
      application: {
        userName: "",
        firstName: "",
        lastName: "",
        emailAddress: "",
        adoptedBefore: "",
        hasCertifications: "",
      },
    };
  },
  methods: {
    submitForm() {
      const newApp = {
        username: this.application.userName,
        firstName: this.application.firstName,
        lastName: this.application.lastName,
        emailAddress: this.application.emailAddress,
        adoptedBefore: this.application.adoptedBefore,
        hasCertifications: this.application.hasCertifications,
      };
      //add the service call here
      volunteerService
        .apply(newApp)
        .then((response) => {
          if (response.status === 200) {
            alert("Thank you for your application!");
            this.$router.push("/");
          }
        })
        .catch((error) => {
          alert("Error: try using a different username: " + error.message);
        });
    },
    cancelForm() {
      this.$router.push("/");
    },
  },
};
</script>

<style scoped>
.app-card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  min-height: 50vh;
  font-size: 12pt;
}

.title {
  text-align: center;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  font-size: 2.5em;
  padding: 30px;
  margin-left: 2vw;
}
#userName {

  height: 30px;
}
#fname {
  
  height: 30px;
}
#lname {
  
  height: 30px;
}
#email {
  
  height: 30px;
}

.volunteer-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 60vw;
  background-color: rgba(255, 255, 255, 0.356);
  border: 2px solid rgb(0, 0, 0);
  padding: 35px;
  border-radius: 45px;
  margin: 2.5vh 20vw 2.5vh 25vw;
}
.text-boxes {
  display: flex;
  flex-direction: column;
  text-align: left;
  justify-content: space-between;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  font-size: 1.85em;
  margin: 0 4.5vw 0 4.5vw;
}
.radio-btns {
  text-align: center;
  font-size: 0.85em;
}

button {
  padding: 10px;
  width: 25vw;
  font-size: 1.6em;
}
@media (max-width: 1000px) {
  .text-boxes {
    font-size: 1em;
  }
  .title {
    font-size: 2em;
    margin-left: 5%;
  }
  .volunteer-form {
    margin: 0 10vw 2.5vh 20vw;
  }
  .radio-btns {
    font-size: 1em;
  }
  button {
    font-size: 1em;
  }
}

@media (max-width: 700px) {
 .text-boxes {
    font-size: 1em;
  }
  .title {
    font-size: 1.75em;
    margin-left: 5%;
  }
  .volunteer-form {
    margin: 0 10vw 2.5vh 20vw;
  }
  .radio-btns {
    font-size: 1em;
  }
  button {
    font-size: 1em;
  }
}
@media (max-width: 410px){
  .text-boxes {
    font-size: 1em;
  }
  .title {
    font-size: 1.5em;
    margin-left: 2em;
  }
  .volunteer-form {
    margin: 0 1vw 2.5vh 4em;
  }
  .radio-btns {
    font-size: .85em;
  }
  .buttons {
    display: flex;
    flex-direction: row;
    font-size: .85em;
  }
    #userName {
    width: 180px;
  }
  #fname {
    width: 180px;
  }
  #lname {
    width: 180px;
  }
  #email {
    width: 180px;
  }
  .buttons{
    margin-left: 1.4em;
  }
}
@media (max-width: 300px) {
  .app-card{
    margin-left: .75em;
  }
  .text-boxes {
    font-size: .75em;
    margin-left: 1em;

  }
  .title {
    font-size: 1.75em;
    align-items: center;
  }
  .volunteer-form {
    margin-left: 3.2em;
  }
  .radio-btns {
    font-size: .85em;
  }
  button {
    font-size: .85em;
    margin-left: .4em;
  }
  #userName {
    width: 180px;
  }
  #fname {
    width: 180px;
  }
  #lname {
    width: 180px;
  }
  #email {
    width: 180px;
  }
}
</style>