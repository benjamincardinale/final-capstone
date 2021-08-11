<template>
  <div class="main">
      <h1>Volunteer Directory</h1>
      <div class="table-contact">
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

<style scoped>
.main {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 50vh;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 14pt;
}
.contact-list {
    border-collapse: collapse;
    height: 25px;
    table-layout: auto;
    width: 100%;
    text-align: center;
    margin: 2.5vh 0 5vh 0;
}
.contact-list th {
    border: 1px solid black;
    width: 200px;
    padding: 10px;
}
.contact-list th:last-child {
    width: 237px;
}
.contact-list td {
    padding: 10px;
    border: 1px solid black;
    width: 200px;
    text-align: left;
}
.contact-list td:last-child {
    width: 228px;
}
@media (max-width: 1300px) {
    .contact-list {
        margin-left: 8vw;
    }
}

</style>