<script setup>
import store from "@/store/index";
import { reactive } from 'vue';

const data = reactive({
  currentUser: store.state.auth.user
});

function loggedIn() {
  return data.currentUser ? true : false;
}

function showStudent() {
  return loggedIn() && data.currentUser.roles.includes('ROLE_ETUDIANT');
}

function showTutor() {
  return loggedIn() && data.currentUser.roles.includes('ROLE_TUTEUR');
}

function showResp() {
  return loggedIn() && data.currentUser.roles.includes('ROLE_RESPONSABLESTAGES');
}
</script>

<template>
  <div>
    <div id="nav">
      <router-link to="/">Home |</router-link>
      <router-link v-if="showResp()" to="/allInternships"> Tous les stages |</router-link>
      <router-link v-if="showResp() || showStudent()" to="/freeInternships"> Stages disponibles |</router-link>
      <router-link v-if="showStudent()" :to="{ name: 'InternshipsByStudent', params: { id: data.currentUser.id } }"> Mes stages |</router-link>
      <router-link v-if="showTutor()" :to="{ name: 'InternshipsByTutor', params: { id: data.currentUser.id } }"> Mes stages |</router-link>
      <router-link v-if="showResp()" to="/pendingInternships"> Stages à valider |</router-link>
      <router-link v-if="showResp()" to="/inProgressInternships"> Stages en cours |</router-link>
      <router-link v-if="showResp()" to="/companies"> Entreprises |</router-link>
      <router-link v-if="showResp()" to="/tutors"> Tuteurs |</router-link>
      <router-link v-if="showResp()" to="/students"> Étudiants |</router-link>
      <router-link v-if="showResp() || showStudent()" to="/internshipForm"> Ajout stage |</router-link>
      <router-link v-if="showResp()" to="/companyForm"> Ajout entreprise |</router-link>
      <router-link v-if="!loggedIn()" to="/signup"> Inscription |</router-link>
      <router-link v-if="!loggedIn()" to="/signin"> Connexion |</router-link>
      <router-link v-if="loggedIn()" to="/signout"> Déconnexion |</router-link>
      <router-link v-if="loggedIn()" to="/profile"> Profil |</router-link>
      <router-link to="/about"> About</router-link>
    </div>
    <router-view />

  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
  border: solid;
  background-color: rgb(132, 159, 210);
  z-index: 1;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: rgb(57, 97, 172);
}
</style>
