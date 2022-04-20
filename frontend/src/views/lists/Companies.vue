<script setup>
import { reactive, onMounted } from "vue";

let data = reactive({
  entreprises: [],
});

onMounted(
  getEntreprise,
);

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
</script>

<template>
 <div class="container">
   <h1>Liste des entreprises</h1>
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Nom</th>
          <th>NumTel</th>
          <th>SecteurActivite</th>  
          <th>Email</th>         
        </tr>
      </thead>
      <tbody>
        <tr v-for="entreprise in data.entreprises" :key="entreprise.id">
          <td>{{ entreprise.nom }}</td>
          <td>{{ entreprise.numTel }}</td>
          <td>{{ entreprise.secteurActivite }}</td>
          <td>{{ entreprise.email }}</td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<style>

</style>
