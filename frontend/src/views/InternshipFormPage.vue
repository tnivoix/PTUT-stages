<template>
  <div>
    <IntershipForm ref="formulaire" />
  </div>
</template>

<script setup>
import IntershipForm from "../components/InternshipForm.vue";
import { ref } from 'vue'

const formulaire = ref(null);

</script>

