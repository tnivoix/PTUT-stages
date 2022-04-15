<script setup>
import { reactive, onMounted } from "vue";

let data = reactive({
  etudiants: [],
});

defineExpose({ // On expose la méthode 'getetudiants' pour être utilisée par le parent
  getEtudiants,
})



function getEtudiants() {
  const fetchOptions = {method: "GET"};
  fetch("api/allStudents", fetchOptions)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.etudiants = json;
    })
    .catch((error) => alert(error));
}

onMounted(getEtudiants);






</script>

<template>
 <div class="container">
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Identifiant</th>
          <th>MotDePasse</th>
          <th>Prenom</th>  
          <th>Nom</th> 
          <th>NumTel</th>
          <th>Email</th>        
        </tr>
      </thead>
      <tbody>
        <tr v-for="etudiant in data.etudiants" :key="etudiant.id">
          <td>{{ etudiant.identifiant }}</td>
          <td>{{ etudiant.motDePasse }}</td>
          <td>{{ etudiant.prenom }}</td>
          <td>{{ etudiant.nom }}</td>
          <td>{{ etudiant.numTel }}</td>
          <td>{{ etudiant.email }}</td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<style>

</style>
