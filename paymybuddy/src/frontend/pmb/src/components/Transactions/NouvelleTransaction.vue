<template>
  <div class="gestion_entete">
    <h2>Envoyer de l'argent<NouvelleConnexion/></h2>
  </div>
  <div class="carre" style="background-color:lightgrey;">
    <div class="boutons">
      <select class="utilisateur-info" name="utiliateur-selection" v-model="selectedUtilisateur">
        <option value="">Envoyer de l'argent</option>
        <option v-for="(utilisateurs, index) in utilisateurs" :key="index" :value="item.utilisateurs">
            {{item.utilisateurs}}
          </option>
      </select>
      {{ selectedUtilisateur }}
      <input type="number" id="tentacles" name="tentacles" min="01" max="100000"/>
      <button class="button2">Payer</button>
    </div>
    </div>
</template>



<script>
import TransactionsDataServices from '../../services/TransactionsDataServices';
import UtilisateurDataServices from '@/services/UtilisateurDataServices';
import NouvelleConnexion from '../Connexions/NouvelleConnexion.vue';


export default {
    name: "transaction-item",
    data() {
        return {
            transaction: {
                Date: Date,
                descritption: "",
                Montant: 0,
                emetteur: "",
                destinataire: "",
            },
            NouvelleConnexion,
            UtilisateurDataServices,
            submitted: false,
            selectedUtilisateur:"",
            utilisateurs: [],
        };
    },
    methods: {
        create() {
            TransactionsDataServices.create(this.data)
                .then(response => {
                this.transaction.emetteur = response.data.emetteur;
                this.submitted = true;
            })
                .catch(e => {
                alert(e);
            });
          },
        newTransaction() {
            this.submitted = false;
            this.transaction = {};
        },
        loadFromApi(){
          this.utilisateurs=[
            UtilisateurDataServices.getAll()
          ]
        },
        created(){
          this.loadFromApi();
        },
    },
    components: { NouvelleConnexion },
}
  

</script>

<style>
select{
  height: 50px;
  width: 350px;
  margin-left: 120px;
}
label {
  display: block;
  font: 1rem 'Fira Sans', sans-serif;

}
h2{
  margin-left: 500px;
  font-weight: bold;
  font-size: xx-large;
  margin-top: auto;
  margin-bottom: auto;
}
.boutons{
  margin-left: 250px;
  margin-right: auto;
  margin-top: 50px;
}

input,
label {
  height: 45px;
  width: 150px;
  border-radius: 5px / 5px;
  margin-left: 20px;
}
.button2{
  background-color: green;
  color: #fff;
  height: 50px;
  width: 250px;
  border:1px green;
  border-radius: 20px / 20px;
  margin-left: 25px;
  font-weight: bold;
  font-size: x-large;
}
.rectangle{
  width: 2600px;
  height: 50px;
  font-size: x-large;
}
.gestion_entete{
  display: inline-block;

}
.carre {
  display: flex;
  width: 1550px;
  height: 150px;
  margin-left: 500px;
  
}
</style>