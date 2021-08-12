<template>
  <div class="main">
    <img src="../assets/CatsNDogs.png"
           alt="Picture of Cats and Dogs" />
    <div class="first-box">
      <h2 class="title1"><span>Overview</span></h2>
      <p class="body1">
        <span
          >Established in 2021, Second Chances has quickly become a leading
          force for good in the local community. We believe the bond between
          "hoomans" and animals serves an integral role in the overall health of
          our society. Being a volunteer-driven organization, we rely on the
          warm hearts of people like you to ensure the benefits of Human-Animal
          Interaction (HAI) continue to grow and permeate throughout our
          culture. Continue reading to learn how to get involved!</span
        >
      </p>
    </div>
    <div class="second-box">
      <h2 class="title2"><span>Interested in Adopting?</span></h2>
      <p class="body2">
        <span
          >If you see a listing on our site for a pet you want to take home,
          please come visit us at the address listed at the bottom of the page.
          We are open from 9am to 8pm M-F and from 10am-6pm on Saturdays. You
          can also call us at 513-555-5555 to schedule an appointment. Our staff
          are as friendly as our animals and nothing beats our in-store
          experience!</span
        >
      </p>
    </div>
    <div class="third-box">
      <h2 class="title2"><span>Join Us Today!</span></h2>
      <p class="body2">
        <span
          >Anyone is welcome to view our current selection of furry friends by
          navigating to our home page. You can also submit an application to
          become a volunteer with us by selecting "Volunteer Registration" in
          the navigation bar on the left of the screen. If you are a current
          user, please select the "Login" option. Finally, if you are an
          authorized user and wish to submit a pet for adoption, please select
          the "Add Pet" button near the top of the page to get started! (Note this button is hidden for unauthorized users.)</span
        >
      </p>
    </div>
    <div class="fourth-box">
      <h2 class="title2"><span>The Devs</span></h2>
      <p class="body2">
        <span
          >Thank you for visiting our site! <br /> Learn more about the developers here: <br /><br /> <a href="https://www.linkedin.com/in/benjamin-cardinale/">Ben Cardinale</a>
          <br /><a href="https://www.linkedin.com/in/henry-johnson-401908172/">Henry Johnson</a>
          <br /><a href="https://www.linkedin.com/in/terrell-mallory/">Terrell Mallory</a>
          <br /><a href="https://www.linkedin.com/in/nateshrader/">Nate Shrader</a>
          </span>

      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "about-page",
};
</script>

<style scoped>
.main {
  display: flex;
  flex-direction: column;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  font-size: 16pt;
}

.first-box {
  margin: 2.5% 4% 1% 18%;
}

.second-box {
  margin: 2.5% 4% 1% 18%;
}

.third-box {
  margin: 2.5% 4% 1% 18%;
}

.fourth-box {
  margin: 2.5% 4% 1% 18%;
}

p {
  border: 3px;
  border-style: ridge;
  border-color: rgb(224, 224, 213);
  padding: 20px;
  background-color: rgb(228, 228, 218, 0.8);
  margin-top: 5%;
}

h2 span {
  background: rgba(241, 138, 41, 0.219);
  font-size: 0.85em;
}

img {
  height: 30%;
  width: 50%;
  margin: 5% 4% 1% 18%;
  align-self: center;
  border: 10px double;
  border-radius: 2%;
  flex-grow: 1;
}
@media (max-width: 1000px) {
  .top-box {
    margin: 2.5% 3% 1% 17%;
  }
  .bottom-box {
    margin: 2.5% 5% 10% 17%;
  }
}
@media (max-width: 700px) {
  .left-box {
    margin-left: 3.5em;
  }
}
@media (max-width: 460px){
  .main{
    margin-left: 1em;
  }
}
@media (max-width: 350px){
  .main{
    margin-left: 1.5em;
  }
}

</style>