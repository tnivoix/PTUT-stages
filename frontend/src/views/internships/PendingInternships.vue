<script setup>
import InternshipsList from "@/components/InternshipsList.vue";

var link = "pendingInternships";
</script>

<template>
<h1>Liste de tous les stages en attente de validation</h1>
<InternshipsList :link="link"/>
</template>


<style>

</style>