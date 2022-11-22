<template>
  <div class="container">
    <div class="row col-md-6 col-md-offset-3">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Inscription</h1>
      </div>
      <div class="card-body"></div>
      <form action="app.js" method="post">
        <div class="form-group">
          <label for="Nom"> Nom </label>
          <input type="text" name="nom" id="nom" v-model="utilisateurDAO.nom"/>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="Prenom"> Prenom </label>
          <input type="text" name="prenom" id="prenom" v-model="utilisateurDAO.prenom"/>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="DateDeNaissance"> Date de naissance </label>
          <input type="date" name="dateDeNaissance" id="dateDeNaissance" v-model="utilisateurDAO.dateDeNaissance" />
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="AdresseEmail"> Addresse Email </label>
          <input type="text" name="adresseEmail" id="adresseEmail" v-model="utilisateurDAO.adresseEmail"/>
        </div>
      </form>
    </div>
    <div class="form-group">
          <label for="motDePasse">Mot de passe</label>
          <input v-model="utilisateurDAO.motDePasse" type="password" name="motDePasse" id="motDePasse"/>
    </div>        
    <div class="card-footer">
      <button v-on:click="create()" class="btn btn-primary">
      
      </button>
    </div>
  </div>
</template>

<script>
import UserServices from "../services/utilisateurService";

export default {
  name: "inscription-item",
  data() {
    return {
      utilisateurDAO: {
        nom: "",
        prenom: "",
        adresseEmail: "",
        dateDeNaissance: "",
        motDePasse:""
      },
    };
  },
  methods: {
    create() {
      UserServices.create(this.utilisateurDAO)
        .then((response) => {
          this.utilisateurDAO = response.data;
          this.submitted = true;
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
};
</script>

<style scoped>
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
