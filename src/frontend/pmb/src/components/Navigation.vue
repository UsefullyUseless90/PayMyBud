<template>
   <div id="nav">
        <router-link to="/">Accueil</router-link> |
        <router-link to="/transaction/transactions">Transactions</router-link> |
        <router-link to="/editionProfil">Profil</router-link> |
        <router-link to="/connexions/nouvelleConnexion">Contact</router-link> |
        <router-link to="/logout">Déconnexion</router-link>|
        <router-view/>
      </div>
      
</template>
<script>

export default{
  name: "navigation-item"
}
</script>