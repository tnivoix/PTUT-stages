<script setup>
import InternshipsList from "@/components/InternshipsList.vue";
import UserService from '@/services/user.service';
import { reactive } from 'vue';

var link = "pendingInternships";

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
<h1>Liste de tous les stages en attente de validation</h1>
<InternshipsList :link="link"/>
  </div>
  <div v-else>
    Vous n'êtes pas autorisé à accéder à cette page.
  </div>
</template>


<style>

</style>