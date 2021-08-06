<template>
  <div class="main">
      <h2 class= "title"> Volunteers Awaiting Approval</h2>
      <table class="volunteer-table">
          <tr class="table-head">
            |&nbsp;  <th> ID </th> |&nbsp;
              <th> Username</th> |&nbsp;
              <th> First name</th> |&nbsp;
              <th> Last name</th> |&nbsp;
              <th> E-mail</th> |&nbsp;
              <th> Adopted before</th> |&nbsp;
              <th> Certifcations</th> |&nbsp;
          </tr>
            <tr class="volunteer-object-loop" v-for="item in $store.state.pendingApplications" v-bind:key="item.id">
                <td>{{item.id}}</td>
                <td>{{item.userName}}</td>
                <td>{{item.firstName}}</td>
                <td>{{item.lastName}}</td>
                <td>{{item.emailAddress}}</td>
                <td>{{item.adoptedBefore}}</td>
                <td>{{item.hasCertifications}}</td>

            </tr>

      </table>



  </div>
</template>

<script>
import volunteerService from '../services/VolunteerService.js'

export default {
    name: "volunteer-approval",

    data() {
        return {
            approvedVolunteer: {
                id: "",
                userName: "",
                firstName: "",
                lastName: "",
                emailAddress: "",
                adoptedBefore: "",
                hasCertification: "",
                status: "", //use "approved" "declined"
            }

        }
    },
    created() {
        this.retrievePending();
    },
    methods: {
        retrievePending() {
            volunteerService.getPending().then(response => {
                this.$store.commit("SET_PENDING_APPLICATIONS", response.data);
            })
        },
    }
}    
</script>

<style>

.main {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 50vh;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 14pt;
}
.table-headers {

}

</style>