<template>
  <div class="container">
    <img src="logoPMB.png" alt="Pay My Buddy Logo" class="pic" />
    <div class="row col-md-6 col-md-offset-3">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Inscription</h1>
      </div>
      <div class="card-body"></div>
      <form action="app.js" method="post">
        <div class="form-group">
          <label for="Nom"> Nom </label>
          <input type="text" class="form-control" id ="nom"/>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="Prenom"> Prenom </label>
          <input type="text" class="form-control" id ="prenom"/>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="Datedenaissance"> Date de naissance </label>
          <input type="text" class="form-control" id ="dateDeNaissance"/>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="AdresseEmail"> Addresse Email </label>
          <input type="text" class="form-control" id ="adresseEmail"/>
        </div>
      </form>
    </div>
    <div class="card-footer">
      <button @click="inscription" class="btn btn-primary">Inscription</button>
    </div>
  </div>
</template>

<script>
  import UtilisateurDataServices from '../../services/UtilisateurDataServices';


export default {
    name: 'edition-utilisateur',
        data()
        {
          return {
            utilisateurActuel: null,
            message:''
        }
      },
      methods: {
      getUtilisateur(id)
            {
              UtilisateurDataServices.get(id)
                  .then(response => {
                    this.utilisateur = response.data
                  })
                  .catch(e => {
                    alert(e)
                  })
            },
            miseAJourUtilisateur(){
            UtilisateurDataServices.update(this.utilisateurActuel.id, this.utilisateurActuel)
                .then(() => {
                    this.message = "La mise à jour de l'utilsateur à été réalisée avec succès!"
                })
                .catch(e => {
                    alert(e)
                })
        },
        deleteCustomer() {
            UtilisateurDataServices.delete(this.currentCustomer.id)
                .then(() => {
                    this.$router.push({name: 'utilisateurs'})
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.getUtilisateur(this.$route.params.id)
    }
}
</script>

<style scoped>

</style>