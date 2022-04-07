<script setup>
  import SignUpCompany from "@/components/SignUpCompany.vue";
  import ListEntreprise from "@/components/ListEntreprise.vue";
  import { ref } from "vue";

const list = ref(null);

function refresh() {
  list.value.getEntreprises();
}

</script>

<template>
  <div>
    <SignUpCompany @entrepriseAdded="refresh"/>
    <ListEntreprise ref="list"/>
  </div>
</template>

<style>

</style>