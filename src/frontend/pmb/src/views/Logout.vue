<template>
   <button v-on:click="logout()" class="btn btn-primary">Deconnexion</button>
</template>
<script>
import UtilisateurDataServices from '../services/UtilisateurDataServices'
export default{
 name: "logout-item",
  methods:{
    logout(){
      UtilisateurDataServices.logout()
    }
  }
}
</script>