<template>
  <div class="gestion_entete">
    
  </div>
    <table class="table">
      <tbody v-for="(connexions, index) in connexions" :key="index">
        <tr>
          <td>{{ connexions.idUtilisateur}}</td>
          <td>{{ connexions.nomPrenom }}</td>
        </tr>
      </tbody>
    </table>

</template>
<script>
import ConnexionDataServices from "../../services/ConnexionDataServices";

export default {
  name: "connexions-item",
  data() {
    return {
      connexions: [],
    };
  },
  methods: {
    retrieveConnexions() {
      ConnexionDataServices.get()
        .then((response) => {
          this.connexions = response.data;
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
  onConnexionChange(event) {
    const src = event.target.id; // This should give you the id of the select that has fired the event
    const index = parseInt(src.replace("connexions.id", ""));
    this.requestItems[index].connexions.id = event.target.value;
    console.log(this.requestItems[index].connexions.id);
  },
  retrieveConnexions() {
    ConnexionDataServices.get()
      .then((response) => {
        this.connexions = response.data;
      })
      .catch((e) => {
        alert(e);
      });
  },
  mounted() {
    this.retrieveConnexions();
  },
};
</script>
<style>
  table {
    text-align: center;
    font-weight: bold;
    font-size: xx-large;
    color: #fff;
    height: 150px;
    width: 1550px;
    margin-left: 20%;
  }
  
  td {
    color: black;
  }
  </style>
  