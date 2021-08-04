<template>
  <div class="hidden-btn">
      
        <router-link v-bind:to="{name: ''}" v-if="$store.state.isAuth == true" class="update">Update Pet</router-link>
        <!-- insert update path above -->
        <router-link v-bind:to="{name: 'submit'}" v-if="$store.state.isAuth == true" class="add">Add Pet</router-link>  
      
  </div>
</template>

<script>
export default {
    name: 'hidden-function'
}
</script>

<style scoped>

.hidden-btn {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 16pt; 
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

</style>