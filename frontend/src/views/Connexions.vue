<template>
  <div class="nouvelleConnexion">

    <h1>Connexions</h1>
    
    <ConnexionTableVue/>
    <ConnexionsListeVue/>
    <ConnexionDemandeVue/>
  </div>

</template>

<script>
  
import ConnexionDemandeVue from '@/components/Connexions/ConnexionDemande.vue';
import ConnexionTableVue from '@/components/Connexions/ConnexionTable.vue';
import ConnexionsListeVue from '@/components/Connexions/ConnexionsListe.vue';
  
export default{
  name:"connexion-item",
    components:{
      ConnexionDemandeVue,
      ConnexionTableVue,
      ConnexionsListeVue
    }
 
  }

</script>
<style>
.nouvelleConnexion{
 
}
.nav{
  margin-left: 75%;
}
</style>