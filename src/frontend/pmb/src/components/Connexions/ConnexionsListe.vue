<template>
  <div class="gestion_entete">
    
  </div>
    <table class="table">
      <tbody v-for="(connexion, index) in connexions" :key="index">
        <tr>
          <td>{{ connexion.idUtilisateur}}</td>
          <td>{{ connexion.nomPrenom }}</td>
        </tr>
      </tbody>
    </table>

</template>
<script>
import axios from "axios";
export default {
  name: "connexions-item",
  data() {
    return {
      connexions: [],
    };
  },
  computed:{
      user(){ return this.$store.state.user },
      token(){ return this.$store.state.token },
    },
  methods: {
    retrieveConnexions() {
      axios.get("http://localhost:8080/paymybuddy/user/connexions/1")
        .then((response) => {
          this.connexions = response.data;
        })
        .catch((e) => {
          alert(e);
        });
    },
  logout(){
        this.$store.commit('setUser'      , {} )
        this.$store.commit('setToken'     , '' )
        this.$router.push('http://localhost:8080/paymybuddy/login/signin')        
      },
    },   
  mounted() {
    this.retrieveConnexions();
  },
}

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
  