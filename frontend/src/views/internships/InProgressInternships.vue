<script setup>
import { reactive, onBeforeMount, onMounted } from "vue";
import InternshipsList from "@/components/InternshipsList.vue";

var link = "inProgressInternships";
</script>

<template>
  <h1>Liste de tous les stages en cours</h1>
  <InternshipsList :link="link" />
</template>

<style></style>
