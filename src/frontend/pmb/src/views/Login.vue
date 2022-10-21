<template>
<div class="container">
    <div class="row col-md-6 col-md-offset-3">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Login</h1>
      </div>
        <div class="form-group">
          <label for="AdresseEmail"> Adresse Email </label>
          <input type="text" name="adresseEmail" id="adresseEmail" v-model="adresseEmail"/>
        </div>
    </div>
    <div class="form-group">
          <label for="motDePasse">Mot de passe</label>
          <input v-model="motDePasse" type="password" name="motDePasse" id="motDePasse"/>
    </div>        
    <div class="card-footer">
      <button v-on:click="performLogin()" class="btn btn-primary">Se connecter</button>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'

export default{
  name: "Login-item",
  data() {
    return {
      utilisateurDAO:{
        adresseEmail: "",
        motDePasse:""
      }  
    };
  },
methods: {
        performLogin() {
            let formData = new FormData();
            formData.set("username", this.adresseEmail);
            formData.set("password", this.motDePasse);
            const CSRF_TOKEN = document.cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`));
            const instance = Axios.create({
            headers: { "X-XSRF-TOKEN": CSRF_TOKEN }
            });
            const AXIOS = instance;
            Axios.post("http://localhost:8080/paymybuddy/login", formData,{headers: AXIOS})
             .then((result) => {
              console.log(result);
             window.location = '/accueil';
             })
             .catch((error) => {
             console.error(error)
             })
        }
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