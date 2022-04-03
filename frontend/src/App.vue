<script setup>
import PageStage from "@/View/PageStage.vue";
</script>

<template>
  <div>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
      <router-view />
    </div>
  </div>
 <PageStage />
</template>

<style>

</style>
