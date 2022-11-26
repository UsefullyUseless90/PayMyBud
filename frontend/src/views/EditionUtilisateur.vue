<template>
  <div class="container">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Informations Personnelles</h1>
      </div>
      <div class="card-body"></div>
      <form action="app.js" method="put">
        <div class="form-group">
          <label for="Nom"> Nom </label>
          <input type="text" class="form-control" id="nom" v-model="utilisateurDAO.nom"/>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="Prenom"> Prenom </label>
          <input type="text" class="form-control" id="prenom" v-model="utilisateurDAO.prenom" />
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="AdresseEmail" > Addresse Email </label>
          <input type="text" class="form-control" id="adresseEmail" v-model="utilisateurDAO.adresseEmail"/>
        </div>
        <button @click="update()" class="btn btn-primary">Validation Changement Informations</button>
      </form>
  </div>
      
</template>

<script>
import UserServices from "../services/utilisateurService";

export default {
  name: "edition-utilisateur",
  data() {
    return {
      utilisateurDAO:{
        nom:"",
        prenom:"",
        adresseEmail:""
      }
    };
  },
  methods: {
    update() {
      UserServices.update(this.userId, this.utilisateurDAO)
      .then((response) =>{
        this.utilisateurDTO = {...response.data}
        this.$router.push('/profile')
      },
      error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
            }
      )
    },
    onSave() {
      if (this.currentUser.motDePasse === this.currentUser.confirmMotDePasse) {
        UserServices.update(this.userId, this.currentUser).then(
          () => {
            this.$store.dispatch("auth/logout");
            var user = {
              email: this.currentUser.adresseEmail,
              password: this.currentUser.motDePasse,
            };
            this.$store.dispatch("auth/login", user).then(
              () => {
                this.$router.push("/home");
              },
              (error) => {
                alert(
                  error.response.data.error || error.message || error.toString()
                );
              }
            );
          },
          (error) => {
            alert(error.response.data || error.message || error.toString());
          }
        );
      } else {
        alert("Les mots de passe doivent être identiques!");
      }
    },
  },
  computed: {
    userId: function () {
      return this.$store.state.auth.user.idUtilisateur;
    },
  },
};
</script>

<style scoped>
.container{
margin-top: 5%;
margin-left: 20%;
font-weight: bold;
font-size: x-large;
display: block;
}
label{
  font-weight: bold;
  font-size: x-large;
  width: 300px;
}
input{
  width: 300px;
}
.nav{
  margin-left: 75%;
}
button{
  background-color: green;
  color: #fff;
  height: 50px;
  border: 1px green;
  border-radius: 20px / 20px;
  font-weight: bold;
  font-size: x-large;
  margin-top: 25px;
  width: 300px;
  height: 80px;
  margin-left: 2%;

}
</style>
