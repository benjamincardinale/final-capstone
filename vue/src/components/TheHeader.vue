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
  background: linear-gradient(90deg, rgba(233, 128, 116), rgba(233, 128, 116),rgba(255,203,154,.5)/*rgba(214, 126, 126, 0.479)*/);
  border-radius: 25px;
  padding-top: 15px;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  margin-bottom: 100px;
  text-align: center;
  justify-content: space-around;
  margin-top: 50px;
}

.header-text h1 {
  margin-top: -0.3em;
  margin-bottom: -0.4em;
  text-decoration: underline;
  font-size: 100px;
}
h2 {
  font-size: 45px;
  margin-bottom: 10px;
}
.header-logo {
  max-height: 150px;
  max-width: 140px;
  flex-grow: 1;
  margin-left: 300px;
}
.header-text {
  flex-grow: 2;
  padding-right: 350px;
}
</style>