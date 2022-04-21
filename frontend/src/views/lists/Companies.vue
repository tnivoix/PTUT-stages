<script setup>
import { reactive, onMounted } from "vue";
import UserService from '@/services/user.service';
import router from "@/router/index";

let data = reactive({
  entreprises: [],
  allowed: false
});

onMounted(
  getEntreprise,
);

function internshipsRedirect(id) {
    router.push("/internshipsByCompany/"+id);
}

function getEntreprise() {
  const fetchOptions = {method: "GET"};
  fetch("/api/allCompanies", fetchOptions)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.entreprises = json;
    })
    .catch((error) => alert(error));
}

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
 <div v-if="data.allowed" class="container">
   <h1>Liste des entreprises</h1>
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Nom</th>
          <th>NumTel</th>
          <th>SecteurActivite</th>  
          <th>Email</th>
          <th>Stages</th>         
        </tr>
      </thead>
      <tbody>
        <tr v-for="entreprise in data.entreprises" :key="entreprise.id">
          <td>{{ entreprise.nom }}</td>
          <td>{{ entreprise.numTel }}</td>
          <td>{{ entreprise.secteurActivite }}</td>
          <td>{{ entreprise.email }}</td>
          <td>
            <button @click="internshipsRedirect(entreprise.id)">Stages</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <div v-else>
    Vous n'êtes pas autorisé à accéder à cette page.
  </div>
</template>

<style>

</style>
