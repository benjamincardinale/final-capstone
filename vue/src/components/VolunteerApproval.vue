<template>
  <div class="main">
      <h2 class= "title"> Volunteers Awaiting Approval</h2>
      <table class="volunteer-table">
          <tr class="table-head">
              <th>| ID | </th>
              <th> |Username| </th> 
              <th>|First|</th> 
              <th> |Last|</th> 
              <th>|E-mail Address| </th> 
              <th> |Adopted before| </th> 
              <th> |Certifcations|</th> 
          </tr>
            <tr class="volunteer-object-loop" v-for="item in $store.state.pendingApplications" v-bind:key="item.id">
                <td>
                    <input type="checkbox"
                    v-bind:checked="selectedIds.includes(item.id)"
                    v-bind:id="item.id" v-bind:value="item.id"
                    v-on:click="selectedIds.push(item.id)"/>
                </td>
                <td>{{item.id}}</td>
                <td>{{item.username}}</td>
                <td>{{item.firstName}}</td>
                <td>{{item.lastName}}</td>
                <td>{{item.emailAddress}}</td>
                <td>{{item.adoptedBefore}}</td>
                <td>{{item.hasCertifications}}</td>

            </tr>
            <div class="buttons">
                <button class= "reset" v-on:click="resetCheckBoxes()">RESET</button>
                <button class= "approve" v-on:click="approve()">APPROVE</button>
                <button class= "reject" v-on:click="reject()"> REJECT</button>
            </div>

      </table>



  </div>
</template>

<script>
import volunteerService from '../services/VolunteerService.js'

export default {
    name: "volunteer-approval",

    data() {
        return {
            selectedIds: [],

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
        resetCheckBoxes() {
            this.selectedIds = [];
        },
        approve() {
            // send array of selectedIDs to approve endpoint
        },
        reject() {
           // send array of selectedIDs to rejected endpoint     
        }
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

.volunteer-table td {
    padding: 10px;
    border: 1px solid black;

}
.volunteer-table th {
    padding: 2px;
} 

</style>