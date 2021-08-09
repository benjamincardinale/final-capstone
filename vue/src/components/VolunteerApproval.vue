<template>
  <div class="main">
      <h2 class= "title"> Volunteers Awaiting Approval</h2>
      <h3 v-if="$store.state.pendingApplications.length == 0" > No pending applications </h3>
      <table class="volunteer-table" v-else>
          <tr class="table-head">
              <th>Select</th>
              <th>ID</th>
              <th>Username</th> 
              <th>First</th> 
              <th>Last</th> 
              <th>E-mail Address</th> 
              <th>Adopted before</th> 
              <th>Certifcations</th> 
          </tr>
            <tr class="volunteer-object-loop" v-for="item in $store.state.pendingApplications" v-bind:key="item.id">
                <td class="boxes">
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
            volunteerService.putApproved(this.selectedIds).then(response => {
                if(response.status === 200) {
                    alert('Submission Success');
                }    
            })
            .catch(error => {
                alert('Error: ' + error.status);
            })
            this.retrievePending();
            this.resetCheckBoxes();
            
        },
        reject() {
           volunteerService.putDeclined(this.selectedIds).then(response => {
               if(response.status === 200) {
                    alert('Submission Success');
                }  
           })
           .catch(error => {
               alert('Error: ' + error.status);
           })
           this.resetCheckBoxes();   
        }
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
h2 {
    font-size: 2em;
    margin-bottom: 4vh;
}
.volunteer-table {
   max-width: 50vw;
   margin: 0 10% 0 14%;
}

.volunteer-table td {
    padding: 10px;
    border: 1px solid black;
    
}
.volunteer-table th {
    padding: 2px;
}
.buttons {
    display: flex;
    flex-direction: column;
    font-size: .5em;
    max-width: 40px;
    align-items: center;
    margin-left: 30px;
    
}   
button {
    min-width: 100px;
}
td > input {
    max-width: 50px;
}
@media (max-width: 1000px) {
    .main {
        margin: 2.5% 15% 12.5% 30%;
        ;
    }
    .volunteer-table {
        font-size: 9pt;
        
    }
    
}

</style>