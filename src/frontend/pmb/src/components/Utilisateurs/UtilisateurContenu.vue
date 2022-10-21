<template>
  <tbody v-for="(utilisateurs, index) in utilisateurs" :key="index">
    <tr>
      <td>{{ utilisateurs.idUtilisateur }}</td>
      <td>{{ utilisateurs.nom }}</td>
      <td>{{ utilisateurs.prenom }}</td>
      <td>{{ utilisateurs.adresseEmail }}</td>
      <td>{{ utilisateurs.dateDeNaissance }}</td>
      <td>{{ utilisateurs.fondsDisponibles }}</td>
      <td>
        <a :href="'/utilisateurs/' + utilisateurs.id" class="btn btn-primary"
          >Edit</a
        >
      </td>
    </tr>
  </tbody>
</template>
<script>
import UtilisateurDataServices from "../../services/UtilisateurDataServices";

export default {
  name: "utilisateurs-item",
  data() {
    return {
      utilisateurs: [],
    };
  },
  methods: {
    retrieveUtilisateurs() {
      UtilisateurDataServices.getAll()
        .then((response) => {
          this.utilisateurs = response.data;
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
  mounted() {
    this.retrieveUtilisateurs();
  },
};
</script>
<style>
td {
  color: black;
}
</style>
