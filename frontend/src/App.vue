<script setup>
import PageStage from "@/View/PageStage.vue";
import SignUpForm from "@/components/SignUpForm.vue";
import SignUpCompany from "@/components/SignUpCompany";
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
    <PageStage />
  </div>
</template>

<style>

</style>
