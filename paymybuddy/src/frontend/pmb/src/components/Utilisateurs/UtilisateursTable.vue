<template>
    <div>
        <table class="table">
            <thead>
                <tr>
                <th scope="col">Identifiant utilisateur</th>
                <th scope="col">Nom</th>
                <th scope="col">Prenom</th>
                <th scope="col">Adresse Email</th>
                <th scope="col">Date de naissance</th>
                <th scope="col">Fonds disponibles</th>
                </tr>
            </thead>
            <tbody v-for="(utilisateurs, index) in utilisateurs" :key="index">
                <tr>
                    <td>{{utilisateurs.idUtilisateur}}</td>
                    <td>{{utilisateurs.nom}}</td>
                    <td>{{utilisateurs.prenom}}</td>
                    <td>{{utilisateurs.adresseEmail}}</td>
                    <td>{{utilisateurs.dateDeNaissance}}</td>
                    <td>{{utilisateurs.fondsDisponibles}}</td>
                    <td><a :href="'/utilisateurs/' + utilisateurs.id" class="btn btn-primary">Edit</a></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
</script>
<style>
    tr,td {
        color: black;
    }
</style>