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
  import ConnexionsListeVue from '@/components/Connexions/ConnexionsListe.vue';
  import ConnexionTableVue from '@/components/Connexions/ConnexionTable.vue';

export default{
  name:"connexion-item",
  components:{
      ConnexionDemandeVue,
      ConnexionsListeVue,
      ConnexionTableVue
    }
  }


</script>