<template>
  <div>
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
</template>

<script>
export default {
  name: "about-page",
};
</script>

<style scoped>
div {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: 
    "title1 title2"
    "body1 body2";
} 

.title1 {
    grid-area: title1;
}

.title2 {
    grid-area: title2;
}

.body1 {
    grid-area: body1;
}

.body2 {
    grid-area: body2;
}

p {
    border: 3px;
    border-style: ridge;
    border-color:rgb(224, 224, 213);
    padding: 1em;
    margin-left: 5%;
    margin-right: 5%;
    background-color: rgb(228, 228, 218, .8);
    
}

h2 {
    margin-top: -3%;
    margin-bottom: 10%;
}

p span {
  color:rgb(148, 128, 13);
  font-size: 1.5rem;
}

h2 span {
  background: rgba(241, 138, 41, 0.219);
}
</style>