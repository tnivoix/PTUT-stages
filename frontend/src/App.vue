<script setup>
import PageAllStage from "@/views/PageAllStage.vue";
import ListDesStages from "@/views/ListDesStages.vue";
</script>

<template>
  <div>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/listDesStages">Liste des stages disponibles</router-link> |
      <router-link to="/pageAllStage">Liste de tous les stages</router-link> |
      <router-view />
    </div>
  </div>
</template>

<style>

</style>
