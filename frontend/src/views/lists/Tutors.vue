<script setup>
import UsersByRole from "@/components/UsersByRole.vue";
import { ref } from "vue";

const list = ref(null);
var role = "Tutor";

function refresh() {
    list.value.getUsers();
}

</script>

<template>
    <div>
        <h1>Liste des tuteurs</h1>
        <UsersByRole :role="role" ref="list" />
    </div>
</template>

<style>
</style>