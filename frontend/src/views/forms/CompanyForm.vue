<script setup>
import CompanyForm from "@/components/CompanyForm.vue";
import UserService from '@/services/user.service';
import { reactive } from 'vue';

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
    <CompanyForm />
  </div>
  <div v-else>
    Vous n'êtes pas autorisé à accéder à cette page.
  </div>
</template>

<style>
</style>