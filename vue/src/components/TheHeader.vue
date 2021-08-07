<template>
  <div class="the-header">
    <img class="header-logo" src="../assets/Shelter_Logo_Trans.png" alt="" />
    <header class="header-text">
      <h1>Second Chances</h1>
      <h2>Pet Placement Agency</h2>
    </header>
  </div>
</template>

<script>
export default {
  name: "the-header",
};
</script>

<style>
.the-header {
  display: flex;
  flex-direction: row;
  align-items: center;
  background: linear-gradient(90deg, rgba(233, 128, 116), rgba(233, 128, 116),rgba(255,203,154,.5)/*rgba(214, 126, 126, 0.479)*/);
  border-radius: 25px;
  padding-top: 15px;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  text-align: center;
  margin-top: -10px;
  max-height: 26vh;
  
  
  
}

.header-text h1 {
  margin-top: -20px;
  margin-bottom: -40px;
  text-decoration: underline;
  font-size: 10vh;
  padding-left: 15px;
}
h2 {
  padding-top: 10px;
  font-size: 5vh;
  margin-bottom: 10px;
}
.header-logo {
  max-height: 15vh;
  max-width: 10vw;
  flex-grow: 1;
  margin-left: 20vw;
}
.header-text {
  flex-grow: 2;
  padding-right: 30vw;
}
</style>