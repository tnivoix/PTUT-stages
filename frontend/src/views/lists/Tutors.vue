<script setup>
import UsersByRole from "@/components/UsersByRole.vue";
import { ref, reactive } from "vue";
import UserService from '@/services/user.service';

const list = ref(null);
var role = "Tutor";

function refresh() {
    list.value.getUsers();
}

const data = reactive({
  allowed: false
})

function test(){
  UserService.getRespBoard().then(
    response => {
      if(response.ok){
        data.allowed=true;
      }
    }
  )
}

test();
</script>

<template>
    <div v-if="data.allowed">
        <h1>Liste des tuteurs</h1>
        <UsersByRole :role="role" ref="list" />
    </div>
    <div v-else>
    Vous n'êtes pas autorisé à accéder à cette page.
  </div>
</template>

<style>
</style>