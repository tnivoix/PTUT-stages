<script setup>
  import PageStage from "@/views/PageStage.vue";
  import SignUpForm from "@/components/SignUpForm.vue";
  import SignUpCompany from "@/components/SignUpCompany.vue";
  import ListUtilisateur from "@/components/ListUtilisateur.vue";
  import ListEntreprise from "@/components/ListEntreprise.vue";
  import ListStage from "@/components/ListStage.vue";
  import About from "@/views/About.vue";
</script>

<template>
  <div>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/internshipFormPage">Ajout de stage</router-link> |
      <router-link to="/Inscription">Inscription</router-link> |
      <router-link to="/InscriptionEntreprise">Inscription entreprise</router-link> |
      <router-link to="/Connexion">Connexion</router-link>
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
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
