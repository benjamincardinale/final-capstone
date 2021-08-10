<template>
  <div class="main">
      <div class="table=contact">
        <table class= "contact-list">
            <div class="table-head">
                <tr>
                    <th>
                        First Name
                    </th>
                    <th>
                        Last Name
                    </th>
                    <th>
                        Role
                    </th>
                    <th>
                        Contact Information
                    </th>
                </tr>
            </div>
            <div class="table-data">
                <tr v-for="item in $store.state.volunteers" :key="item.id">
                    <td>{{item.firstName}}</td>
                    <td>{{item.lastName}}</td>
                    <td>{{item.username}}
                    <td>{{item.emailAddress}}</td>
                </tr> 
            </div>


        </table>          
      </div>
  </div>
</template>

<script>
import volunteerService from '../services/VolunteerService.js'
export default {
    name: "contact-list",
    data() {
        return {

        };
    },
    created() {
        this.retrieveVolunteers();
    },
    methods: {
        retrieveVolunteers() {
            volunteerService.getDirectory().then(response => {
                this.$store.commit("SET_VOLUNTEERS", response.data);
            })
            .catch(error => {
                alert("Error: " + error.message)
            })
        },
    }

}
</script>

<style>

</style>