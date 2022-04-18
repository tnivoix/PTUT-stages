<script setup>
import InternshipsList from "@/components/InternshipsList.vue";

const props = defineProps(["tutor"]);

var link = "internshipsByTutor/"+props.tutor.id;
</script>

<template>
<h1>Liste de tous les stages du tuteur {{props.tutor.nom}}</h1>
<InternshipsList :link="link"/>
</template>


<style>

</style>
