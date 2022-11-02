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
  computed:{
      user(){ return this.$store.state.user },
      token(){ return this.$store.state.token },
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
    },
    logout(){
        this.$store.commit('setUser'      , {} )
        this.$store.commit('setToken'     , '' )
        this.$router.push('http://localhost:8080/paymybuddy/login/signin')        
      },
      beforeMount(){
        axios.get('/internal',
        {
          headers: {
            user: JSON.stringify(this.user),
            Authorization: 'Bearer ' + this.token
          }
        })
          .then(
              (result) => {
                this.message = result.data;
              }
          )
          .catch(
            error => {
              console.log(error.data)
            }
          )
      },
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

.infos {
  height: 25%;
  width: 25%;
  border-radius: 5px / 5px;
  display: flex;
  justify-content: space-evenly;
  margin-left: 20%;
}
</style>
