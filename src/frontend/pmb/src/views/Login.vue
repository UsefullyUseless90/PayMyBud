<template>
  <div class="container">
      <div class="row col-md-6 col-md-offset-3">
        <div class="card card-primary">
          <div class="card-heading text-center"></div>
          <h1>Login</h1>
        </div>
          <div class="form-group">
            <label for="AdresseEmail"> Adresse Email </label>
            <input type="text" name="adresseEmail" id="adresseEmail" v-model="utilisateurDAO.adresseEmail"/>
          </div>
      </div>
      <div class="form-group">
            <label for="motDePasse">Mot de passe</label>
            <input v-model="utilisateurDAO.motDePasse" type="password" name="motDePasse" id="motDePasse"/>
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
      utilisateurDAO:{
        adresseEmail: "",
        motDePasse:""
      }  
    };
  },

  methods: {

    ...mapMutations(["setUser","setToken"]),
  
    login(){
      let formData = new FormData();
      formData.set("username", this.utilisateurDAO.adresseEmail);
      formData.set("password", this.utilisateurDAO.motDePasse);
      axios.post('http://localhost:8080/paymybuddy/login', formData, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
      .then((result) => {
        console.log(result);
             window.location = '/accueil';
             axios.get('http://localhost:8080/paymybuddy/user/accueil')
      })
      .catch((e) => {
        alert(e);
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