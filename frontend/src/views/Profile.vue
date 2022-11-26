<template>
  <div class="container">
    <header class="jumbotron">
      <h1>
      <strong> Bienvenue {{currentUser.prenom}} {{currentUser.nom}}</strong>
      </h1>
    </header>
   
    <p>
      <strong>Id:</strong>
      {{currentUser.idUtilisateur}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.adresseEmail}}
    </p>
   <p>
    <strong>Fonds Disponibles:</strong>
    {{currentUser.fondsDisponible}}
   </p>
  </div>
</template>

<script>
import UserService from '../services/utilisateurService'
export default {
  name: 'Profile-item',
  data() {
    return {
      currentUser: {
        nom: "",
        prenom:"",
        adresseEmail: "",
        idUtilisateur:0,
        fondsDisponible:0.0,
        motDePasse: "",
        confirmMotDePasse: "",
      },
    };
  },
  methods: {
    onSave() {
      if (this.currentUser.motDePasse === this.currentUser.confirmMotDePasse) {
        UserService.update(this.userId, this.currentUser).then(
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
  mounted() {
    if (this.$store.state.auth.user !== null) {
      UserService.getUtilisateur(this.userId).then(
        (response) => {
          var profile = {
            nom:response.data.nom,
            prenom: response.data.prenom,
            adresseEmail: response.data.adresseEmail,
            idUtilisateur:response.data.idUtilisateur,
            fondsDisponible: response.data.fondsDisponibles,
            motDePasse: "",
            confirmMotDePasse: "",
          };
          this.currentUser = profile;
        },
        (error) => {
          alert(error.response.data.error || error.message || error.toString());
          if (error.response.data.status === 401) {
            this.$store.dispatch("auth/logout");
            this.$router.push("/login");
          } else {
            this.$router.push("/home");
          }
        }
      );
    } else {
      this.$router.push("/login");
    }
  },
};
</script>