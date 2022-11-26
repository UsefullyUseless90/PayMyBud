<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar">
      <a href class="navbar-brand" @click.prevent></a>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/home" class="nav-link">
            <font-awesome-icon icon="home" />Home
          </router-link>
        </li>
          <li>
          <router-link to="/transactions" class="nav-link">Transactions</router-link>
        </li>
        <li class="nav-item">
          <router-link v-if="currentUser" to="/user/edit" class="nav-link">Utilisateur</router-link>
        </li>
        <li class="nav-item">
          <router-link v-if="currentUser" to="/connexions" class="nav-link">Amis</router-link>
        </li>
        <li class="nav-item">
          <router-link v-if="currentUser" to="/banque/import" class="nav-link">Importer des fonds</router-link>
        </li>
        <li class="nav-item">
          <router-link v-if="currentUser" to="/banque/export" class="nav-link">Exporter des fonds</router-link>
        </li>
        <li class="nav-item">
          <router-link v-if="currentUser" to="/banque/compte" class="nav-link">Compte bancaire</router-link>
        </li>
      </div>

      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/register" class="nav-link">
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </a>
        </li>
      </div>
    </nav>

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }

      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_MODERATOR');
      }

      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>
<style>


</style>