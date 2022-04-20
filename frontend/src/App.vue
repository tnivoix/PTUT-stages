<script setup>

</script>

<template>
  <div>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/allInternships">Tous les stages</router-link> |
      <router-link to="/freeInternships">Stages disponibles</router-link> |
      <router-link to="/pendingInternships">Stages à valider</router-link> |
      <router-link to="/inProgressInternships">Stages en cours</router-link> |
      <router-link to="/companies">Entreprises</router-link> |
      <router-link to="/tutors">Tuteurs</router-link> |
      <router-link to="/students">Étudiants</router-link> |
      <router-link to="/internshipForm">Ajout stage</router-link> |
      <router-link to="/companyForm">Ajout entreprise</router-link> |
      <router-link to="/signup">Inscription</router-link> |
      <router-link to="/signin">Connexion</router-link> |
      <router-link to="/about">About</router-link>
      <router-view />
    </div>
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
  border:solid;
  background-color:  rgb(132, 159, 210);
  z-index: 1;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color:  rgb(57, 97, 172);
}
</style>
