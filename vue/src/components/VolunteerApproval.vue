<template>
  <div class="main">
      <h2 class= "title"> Volunteers Awaiting Approval</h2>
      <table class="volunteer-table">
          <tr class="table-head">
              <th>ID</th>
              <th>Username</th>
              <th>First name</th>
              <th>Last name</th>
              <th>E-mail</th>
              <th>Adopted before</th>
              <th>Certifcations</th>
          </tr>
            <tr class="volunteer-object-loop">
                <td>{{item.id}}</td>
                <td>{{item.userName}}</td>
                <td>{{item.firstName}}</td>
                <td>{{item.lastName}}</td>
                <td>{{item.emailAddress}}</td>
                <td>{{item.adoptedBefore}}</td>
                <td>{{item.hasCertifications}}</td>

            </tr>

      </table>



  </div>
</template>

<script>
export default {
    name: "volunteer-approval",

    data() {
        return {
            approvedVolunteer: {
                id: "",
                userName: "",
                firstName: "",
                lastName: "",
                emailAddress: "",
                adoptedBefore: "",
                hasCertification: "",
                status: "", //use "approved" "declined"
            }

        }
    }

}
</script>

<style>

</style>