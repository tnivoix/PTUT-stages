<script setup>
import InternshipsList from "@/components/InternshipsList.vue";

const props = defineProps(["company"]);

var link = "internshipsByCompany/"+props.company.id;
</script>

<template>
<h1>Liste de tous les stages de l'entreprise {{props.company.nom}}</h1>
<InternshipsList :link="link"/>
</template>


<style>

</style>
