<template>
  <div class="the-footer">
    <p id="spayedmessage">All pets have been spayed and neutered</p>
    <p id="contactInfo">
      Phone:513.555.5555&nbsp;|&nbsp;Address:123 Imaginary Lane, Cincinnati OH
    </p>
    <p id="missionStatement">
      “To build a community where people value animals and treat them with
      respect and kindness.”
    </p>
  </div>
</template>

<script>
export default {};
</script>

<style>
.the-footer {
  text-align: center;
  font-weight: 800;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  margin-top: 10%;
}
p {
  margin: 0;
}
@media (max-width: 700px){
  .the-footer{
    font-size: .85em;
  }
}
</style>