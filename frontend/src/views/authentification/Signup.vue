<script setup>
import SignUpForm from "@/components/SignUpForm.vue";
import { ref } from "vue";

const list = ref(null);

function refresh() {
  list.value.getUtilisateurs();
}
</script>

<template>
  <div>
    <SignUpForm @utilisateurAdded="refresh" />
  </div>
</template>

<style>
</style>