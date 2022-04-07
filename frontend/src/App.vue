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
      <router-link to="/about">About</router-link> |
      <router-link to="/Inscription">Inscription</router-link> |
      <router-link to="/InscriptionEntreprise">Inscription entreprise</router-link>
      <router-view />
    </div>
  </div>
</template>

<style>

</style>
