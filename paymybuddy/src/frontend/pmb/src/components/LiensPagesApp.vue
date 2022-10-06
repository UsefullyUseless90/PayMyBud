<template>
  <header class="head">
    <div class="liens">
      <nav>
        <router-link to="/">Accueil</router-link>
        <router-link :to="{name: nouvelleTransaction}">Transactions</router-link>
        <router-link to="/editionProfil">Profil</router-link>
        <router-link to="/listeConnexion">Contact</router-link>
        <router-link to="/deconnexion">Déconnexion</router-link>
      </nav>
      <router-view />
    </div>
    
  
  </header>
  <div class="rectangle" style="background-color: lightgrey">
    <a href="Accueil_pmb.html">Accueil /</a>
  </div>
</template>

<script>

  
export default{
  computed:{
    transactions(){
    return this.$route.params.transactions
    }
  }, 
  methods:{
    goback(){
      window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
    }
  }
}
</script>

<style lang="scss">
.head {
  font-size: 40px;
  height: 200px;
  color: white;
  margin-top: auto;
  display: flex;
  justify-content: space-evenly;
}

.liens {
  text-align: right;
  font-size: x-large;
  display: flex;
  margin-left: 1200px;
  justify-content: space-evenly;
}

.rectangle {
  width: 2600px;
  height: 50px;
  font-size: x-large;
}
</style>
