<template>
  <div class= "main">
    <div class="left-box">
      <h2 class="title1"><span>Overview</span></h2>
      <p class="body1">
        <span>Established in 2021, Second Chances has quickly become a leading force
          for good in the local community. We believe the bond between "hoomans" and
          animals serves an integral role in the overall health of our society.
          Being a volunteer-driven organization, we rely on the warm hearts of
          people like you to ensure the benefits of Human-Animal Interaction (HAI)
          continue to grow and permeate throughout our culture. See box on right for how
          to get involved!</span>
      </p>
    </div>
    <div class="right-box">
      <h2 class="title2"><span>Join Us Today!</span></h2>
      <p class="body2">
        <span>Anyone is welcome to view our current selection of furry friends by
          navigating to our home page. You can also submit an application to
          become a volunteer with us by selecting "Volunteer Registration" in the
          navigation bar toward the top of the webpage. If you are a current user,
          please select the "Login" option. Finally, if you are an authorized user
          and wish to submit a pet for adoption, please select the corresponding
          option in our navigation bar to get started!</span>
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
  flex-direction: row;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  font-size: 16pt;
}
.left-box {
  margin: 2.5% 8% 10% 15%;
}
.right-box {
  margin: 2.5% 10% 8% 5%;
}

p {
    border: 3px;
    border-style: ridge;
    border-color:rgb(224, 224, 213);
    padding: 20px;
    background-color: rgb(228, 228, 218, .8);
    margin-top: 5%;
    
}

h2 span {
  background: rgba(241, 138, 41, 0.219);
  font-size: .85em;
} 

</style>