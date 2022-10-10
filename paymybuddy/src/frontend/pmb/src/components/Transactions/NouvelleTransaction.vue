<template>
  <div class="gestion_entete">
    <h2>Envoyer de l'argent</h2><button class="button1">
    <router-link to="/connexions/nouvelleConnexion">Ajouter contact</router-link>
  </button>
  </div>
  <router-view/>
  <div class="carre" style="background-color: lightgrey">
    <div class="infos">
      <select class="form-control" v-model="transactionDTO.destinataire">
        <option value="">--Utilisateur--</option>
        <option
          v-for="(value, index) in connexions"
          :key="index"
          :value="value">
          {{ value.idUtilisateur }}
          {{ value.nomPrenom }}
        </option>
      </select>
      <input
        type="number"
        id="tentacles"
        name="tentacles"
        min="1"
        max="10000"
        v-model="transactionDTO.montant"
      />
      <input
        type="text"
        id="description"
        name="description"
        v-model="transactionDTO.description"
      />
      <input type="date" id="date" name="date" v-model="transactionDTO.date" />
      <button v-on:click="submit()" class="button2Transactions">Payer</button>
    </div>
  </div>
</template>

<script>
import TransactionsDataServices from "../../services/TransactionsDataServices";
import ConnexionDataServices from "@/services/ConnexionDataServices";
import Connexions from "../../views/Connexions.vue"
export default {
  name: "transactionDTO-item",
  data() {
    return {
      transactionDTO: {
        emetteur: {
          idUtilisateur: 1,
          nomPrenom: "Jeff Nippard",
        },
        destinataire: {
          idUtilisateur: "",
          nomPrenom: "",
        },
        date: "",
        description: "",
        montant: 0,
      },
      Connexions,
      ConnexionDataServices,
      submitted: false,
      connexions: [],
      transactions: [],
    };
  },
  methods: {
    getNow: function () {
      const today = new Date();
      const date =
        today.getFullYear() +
        "-" +
        (today.getMonth() + 1) +
        "-" +
        today.getDate();
      const time =
        today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
      const dateTime = date + " " + time;
      this.timestamp = dateTime;
    },
    submit() {
      TransactionsDataServices.create(this.transactionDTO)
        .then((response) => {
          this.transactionDTO = response.data;
          this.submitted = true;
        })
        .catch((e) => {
          alert(e);
        });
    },
    onConnexionChange(event) {
      const src = event.target.id; // This should give you the id of the select that has fired the event
      const index = parseInt(src.replace("connexions.id", ""));
      this.requestItems[index].connexions.id = event.target.value;
      console.log(this.requestItems[index].connexions.id);
    },
    retrieveConnexions() {
      ConnexionDataServices.get()
        .then((response) => {
          this.connexions = response.data;
        })
        .catch((e) => {
          alert(e);
        });
    },
  },

  mounted() {
    this.retrieveConnexions();
  },
  created() {
    this.getNow();
  },
  components: {},
};
</script>

<style>
.carre {
  width: 1550px;
  height: 150px;
  margin-left: auto;
  margin-right: auto;
}
.gestion_entete {
  font-weight: bold;
  font-size: x-large;
  margin-left: 500px;
  margin-right: 330px;
}
.infos {
  height: 45px;
  width: 25%;
  border-radius: 5px / 5px;
  display: flex;
  justify-content: space-between;
}
.button1 {
  margin-left: 900px;
  background-color: blue;
  color: #fff;
  height: 50px;
  width: 250px;
  border: 1px green;
  border-radius: 20px / 20px;
  font-weight: bold;
  font-size: x-large;
}
.button2Transactions {
  background-color: green;
  color: #fff;
  height: 50px;
  border: 1px green;
  border-radius: 20px / 20px;
  font-weight: bold;
  font-size: x-large;
}
.rectangle {
  width: 2600px;
  height: 50px;
  font-size: x-large;
}
</style>
