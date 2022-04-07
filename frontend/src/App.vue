<script setup>
  import PageStage from "@/views/PageStage.vue";
  import SignUpForm from "@/components/SignUpForm.vue";
  import SignUpCompany from "@/components/SignUpCompany.vue";
  import ListUtilisateur from "@/components/ListUtilisateur.vue";
  import ListEntreprise from "@/components/ListEntreprise.vue";
  import ListStage from "@/components/ListStage.vue";

</script>

<template>
  <div>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
      <router-view />
    </div>
    <SignUpForm/>
    <SignUpCompany/>
    <ListUtilisateur/>
    <ListEntreprise/>
    <ListStage/>
    <!--<PageStage />-->
  </div>
</template>

<style>

</style>
