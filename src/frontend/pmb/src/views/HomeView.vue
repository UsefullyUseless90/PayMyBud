  <script>
import Navigation from "../components/Navigation.vue"
export default{
 
  name: "connexion-id",
 
    data(){
      return {
        utilisateurDTO:{
          idUtilisateur:"",
          nomPrenom:""
        },
        components:{
        Navigation
        }
      }
    },
  }
  </script>

<template>
  <main>
    <div class="nav">
    <router-link to="/accueil">Accueil</router-link> |
        <router-link to="/transaction/transactions">Transactions</router-link> |
        <router-link to="/editionProfil">Profil</router-link> |
        <router-link to="/connexions/nouvelleConnexion">Contact</router-link> |
        <router-link to="/logout">Déconnexion</router-link>|
        <router-view/>
      </div>
    <h1>Accueil</h1>
    <body>
      <nav>
        <button class="button1"><router-link to="/connexions/nouvelleConnexion">Ajouter contact</router-link></button>
      </nav>
      <router-view/>
        <h2>Bienvenue!</h2> 
          <div class="titre">
            <router-link to="/"> Mon profil </router-link>
          </div>
        <h3>{{utilisateurDTO.nomPrenom}}</h3>
        <h3>{{utilisateurDTO.nomPrenom.fondsDisponible}}</h3>
        <button class="button2"><router-link to="/transaction/transactions"> Effectuer une transaction </router-link></button>
    </body>
    <router-view/>
  </main>
</template>
<style>
.nav{
  margin-left: 75%;
}
.pic{
  height: 200px;
}
.head{
  font-size: 40px;
  height: 200px;
  color: white;
  margin-top: auto;
  display: flex;
  justify-content: space-evenly;
  display: flex;
  justify-content: space-evenly;
}
.liens{
  margin-top: auto;
  margin-left: auto;
}
.rectangle{
  width: 2600px;
  height: 50px;
  font-size: x-large;
}
.boutons{
  margin-left: 250px;
  margin-right: auto;
  margin-top: 50px;
}
.button1{
  background-color: blue;
  color: #fff;
  height: 50px;
  width: 250px;
  border-radius: 20px / 20px;
  font-weight: bold;
  font-size: x-large;
}
.titre{
  display: flex;
}
.button2{
  display: flex;
  background-color: green;
  color: #fff;
  height: auto;
  width: 250px;
  border:1px green;
  border-radius: 20px / 20px;
  margin-left: 2000px;
  font-weight: bold;
  font-size: x-large;
}
</style>
