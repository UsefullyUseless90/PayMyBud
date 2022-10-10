<template>
  <img src="../src/assets/logoPMB.png" alt="Pay My Buddy Logo" class="pic">
  <div id="app">
    <div id="nav">
        <router-link to="/">Accueil</router-link> |
        <router-link to="/transaction/transactions">Transactions</router-link> |
        <router-link to="/editionProfil">Profil</router-link> |
        <router-link to="/connexions/nouvelleConnexion">Contact</router-link> |
        <router-link to="/deconnexion">Déconnexion</router-link>|
      </div>
      <h2>Bonjour Jeff!</h2>
      
      <router-view/>
  </div>
</template>

<script>
export default {
  name:'App',
  components:{
  },
}

</script>
<style>
@import url('../src/style/reset.css');
@import url('../src/style/Transactions.css');
  #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  
}
h2{
  margin-left: auto;
}

.pic{
  height: 200px;
}
.nav {
  text-align: right;
  font-size: x-large;
  display: flex;
  justify-content: space-evenly;
}

</style>
