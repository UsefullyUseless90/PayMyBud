<template>
  <div class="container">
      <div class="row col-md-6 col-md-offset-3">
        <div class="card card-primary">
          <div class="card-heading text-center"></div>
          <h1>Login</h1>
        </div>
          <div class="form-group">
            <label for="AdresseEmail"> Adresse Email </label>
            <input type="text" name="adresseEmail" id="adresseEmail" v-model="utilisateurDTO.adresseEmail"/>
          </div>
      </div>
      <div class="form-group">
            <label for="motDePasse">Mot de passe</label>
            <input v-model="utilisateurDTO.motDePasse" type="password" name="motDePasse" id="motDePasse"/>
      </div>        
      <div class="card-footer">
        <button v-on:click="login()" class="btn btn-primary">Se connecter</button>
      </div>
    </div>
  </template>

<script>
import axios from 'axios';

import { mapMutations } from 'vuex';
export default {
  name: 'Login',
  data() {
    return {
      utilisateurDTO:{
        adresseEmail: "",
        motDePasse:""
      }  
    };
  },

  methods: {

    ...mapMutations(["setUser","setToken"]),
  
    login(){
  axios.post('http://localhost:8080/paymybuddy/login/signin', this.utilisateurDTO)
  .then((result)=> {
    this.$store.commit('setUser', result.data[0])
    this.$store.commit('setToken', result.data[1])
    this.$router.push('/accueil')
  })
  .catch(error => {
    console.log(error.data)
  })
},
  },
}
</script>

<style scoped>
.container{
margin-left: 40%;
font-weight: bold;
font-size: x-large;
display: block;
}
label{
  font-weight: bold;
  font-size: x-large;
  width: 300px;
}
input{
  width: 300px;
}
button{
  background-color: green;
  color: #fff;
  height: 50px;
  border: 1px green;
  border-radius: 20px / 20px;
  font-weight: bold;
  font-size: x-large;
  margin-top: 25px;
  margin-left: 6%;
}
</style>