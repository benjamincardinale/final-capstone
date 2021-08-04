<template>
  <div class="the-header">
    <img class="header-logo" src="../assets/Shelter-Logo.jpg" alt="" />
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
  background-color: rgba(241, 138, 41, 0.219);
  border-radius: 25px;
  padding-top: 15px;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  margin-bottom: 1px;
  text-align: center;
  justify-content: space-around;

}

.header-text h1 {
  margin-top: -0.3em;
  margin-bottom: -0.4em;
  text-decoration: underline;
  font-size: 8em;
}
h2 {
  font-size: 3em;
  margin-bottom: 10px;
}
.header-logo{
    max-height: 150px;
    max-width: 140px;
    border-radius: 25px;
    opacity: .5;
    flex-grow:1;
   margin-left: 200px;
}
.header-text{
    flex-grow:2;
    padding-right: 200px;
}
</style>