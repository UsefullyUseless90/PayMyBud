<template>
  <div class="gestion_entete">
  <div class="infos">
        <h3>Nom Prenom</h3>
        <input
          type="text"
          id="nomPrenom"
          name="nomPrenom"
          v-model="connexionDTO.demandeur.nomPrenom"
        />
        <h3>Identifiant Utilisateur</h3>
        <input
          type="text"
          id="idUtilisateur"
          name="idUtilisateur"
          v-model="connexionDTO.demandeur.idUtilisateur"/>
      </div>
      <button v-on:click="submitDemande()" onclick="myFunction()" class="button1">Envoyer la demande</button>
  </div>
</template>
<script>
import ConnexionDataServices from "@/services/ConnexionDataServices";

export default {
  name: "connexion-item",
  data() {
    return {
      connexionDTO: {
        demandeur: {
          nomPrenom: "",
          idUtilisateur: "",
        },
        receveur: {
          nomPrenom: "Jeff Nippard",
          idUtilisateur: 1,
        },
      },
      ConnexionDataServices,
    };
  },
  methods:{
    submitDemande() {
      ConnexionDataServices.create(this.connexionDTO)
        .then((response) => {
          this.connexionDTO = response.data;
          this.submitted = true;
        })
        .catch((e) => {
          alert(e);
        });
    },
    myFunction() {
      location.reload();
    }
  }
};
</script>
<style>
.gestion_entete {
  font-weight: bold;
  font-size: x-large;
  margin-left: 500px;
  margin-right: 330px;
}
.button1 {
  background-color: green;
  color: #fff;
  height: 150px;
  width: 250px;
  border: 1px green;
  border-radius: 20px / 20px;
  font-weight: bold;
  font-size: x-large;
}

.infos {
  height: 25%;
  width: 25%;
  border-radius: 5px / 5px;
  display: flex;
  justify-content: space-evenly;
  margin-left: 20%;
}
</style>
