<template>
  <div class="the-header">
    <img class="header-logo" src="../assets/Shelter_Logo_Trans.png" alt="" />
    <header class="header-text">
      <h1>Second Chances</h1>
      <h2>Pet Placement Agency</h2>
    </header>
    <div class="current-user">
      <p  v-if="$store.state.role">
        You are <br /> signed in as: <br />
        <span>{{ $store.state.user.username }}</span>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "the-header",
};
</script>

<style scoped>
.the-header {
  display: flex;
  flex-direction: row;
  align-items: center;
  background: linear-gradient(
    90deg,
    rgba(233, 128, 116),
    rgba(233, 128, 116),
    rgba(255, 203, 154, 0.5) /*rgba(214, 126, 126, 0.479)*/
  );
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
  font-size: 8vh;
  padding-left: -30px;
}
h2 {
  padding-top: 15px;
  font-size: 4vh;
  margin-bottom: 10px;
  margin-left: 10px;
  white-space: nowrap;
}
.header-logo {
  max-height: 17vh;
  max-width: 8vw;
  flex-grow: 1;
  margin-left: 4vw;
  margin-bottom: 2vw;
  margin-right: -2%;
}
.header-text {
  flex-grow: 1.5;
  padding-right: 33vw;
}
.current-user {
  font-size: 2vh;
  color: rgb(66, 66, 61);
  text-transform: uppercase;
  margin-left: -35%;
  padding-right: 3%;
  padding-bottom: 1%;
}
.current-user span {
  color: rgb(173, 149, 38);
}

@media (max-width: 1000px) {
  .the-header {
    display: flex;
    align-items: center;
    background: linear-gradient(
      90deg,
      rgba(233, 128, 116),
      rgba(233, 128, 116),
      rgba(255, 203, 154, 0.5) /*rgba(214, 126, 126, 0.479)*/
    );
    border-radius: 25px;
    text-align: center;
    max-height: 15vh;
    
    
  }
  .header-text h1 {
    font-size: 3em;
    margin-top: 0.5vh;
    margin-left: 10%;
  }
  .header-text h2 {
    font-size: 1.5em;
    padding: 1em;
    margin-left: 10%;
  }
  .current-user {
    font-size: 1em;
  }
}
@media (max-width: 839px){
.header-text h1 {
  font-size: 2.8em;
}
}
@media (max-width: 802px){
.header-text h1 {
  font-size: 2.3em;
}
}
@media (max-width: 700px) {
  .the-header {
    display: flex;
    align-items: center;
    background: linear-gradient(
      90deg,
      rgba(233, 128, 116),
      rgba(233, 128, 116),
      rgba(255, 203, 154, 0.5) /*rgba(214, 126, 126, 0.479)*/
    );
    border-radius: 25px;
    text-align: center;
    max-height: 15vh;
    font-size: 0.8em;
    margin-top: 0.5px;
  }
  .header-text h1 {
    font-size: 3em;
    padding-bottom: 0.5em;
  }
  .header-text h2 {
    font-size: 1.25em;
    padding: 1em;
  }
  .current-user {
    font-size: .8em;;
  }
}
@media (max-width: 644px) {
.header-text h1 {
    font-size: 2.5em;
    padding-bottom: 0.5em;
  }
}
@media (max-width: 644px) {
.header-text h1 {
    font-size: 1.5em;
    padding-bottom: 0.5em;
  }
}
</style>