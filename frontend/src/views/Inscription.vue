<script setup>
import SignUpForm from "@/components/SignUpForm.vue";
import ListUtilisateur from "@/components/ListUtilisateur.vue";
import { ref } from "vue";

const list = ref(null);

function refresh() {
  list.value.getUtilisateurs();
}
</script>

<template>
  <div>
    <SignUpForm @utilisateurAdded="refresh" />
    <ListUtilisateur ref="list" />
  </div>
</template>

<style>
</style>