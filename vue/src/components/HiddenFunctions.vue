<template>
  <div class="hidden-btn">
      
        <router-link v-bind:to="{name: 'update'}" v-if="$store.state.role != ''" class="update">Update Pet</router-link>
        
        <router-link v-bind:to="{name: 'volunteer-pending'}" v-if="$store.state.role == 'ROLE_ADMIN'" class="admin">ADMIN PAGE</router-link>
            <!-- CHANGE TO ACTUAL PATH ONCE CREATED -->
        <router-link v-bind:to="{name: 'submit'}" v-if="$store.state.role != ''" class="add">Add Pet</router-link>  

  </div>
</template>

<script>
export default {
    name: 'hidden-function',
    data() {
        return {
     
        };
    },
    methods: {
       
    }
    
}
</script>

<style scoped>

.hidden-btn {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 16pt; 
    margin-top: 15px;
}
.add {
    border: 2px solid rgba(248, 203, 4, 0.753);
    padding: 5px;
    text-decoration: none;
    background-color: rgba(241,138,41,0.479);
    color: black;
    
}
.add:hover {
    text-decoration: underline;
}
.add:visited {
    color: seashell;
}

.update {
    border: 2px solid rgba(248, 203, 4, 0.753);
    padding: 5px;
    text-decoration: none;
    background-color: rgba(241,138,41,0.479);
    color: black;
}

.update:hover {
    text-decoration: underline;
}
.update:visited {
    color: seashell;
}

.admin {
    border: 2px solid rgba(248, 203, 4, 0.753);
    padding: 5px;
    text-decoration: none;
    background-color: rgba(241,138,41,0.479);
    color: black;
}
.admin:hover {
    text-decoration: underline;
}
.admin:visited {
    color: seashell;
}

</style>