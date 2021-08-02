<template>
  <div id="app">
    <header>
      <div id="nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link
        >&nbsp;|&nbsp;
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
      </div>
      <router-view />
    </header>

    <aside class="sidebar-left">
    
    </aside>
    <p>

    </p>
    <footer>

    </footer>
    
  </div>
</template>
