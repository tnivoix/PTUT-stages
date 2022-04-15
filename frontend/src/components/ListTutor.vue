<script setup>
import { reactive, onMounted } from "vue";

let data = reactive({
  tuteurs: [],
});

defineExpose({ // On expose la méthode 'gettuteurs' pour être utilisée par le parent
  getTuteurs,
})



function getTuteurs() {
  const fetchOptions = {method: "GET"};
  fetch("api/allTutors", fetchOptions)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.tuteurs = json;
    })
    .catch((error) => alert(error));
}

onMounted(getTuteurs);






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
        <tr v-for="tuteur in data.tuteurs" :key="tuteur.id">
          <td>{{ tuteur.identifiant }}</td>
          <td>{{ tuteur.motDePasse }}</td>
          <td>{{ tuteur.prenom }}</td>
          <td>{{ tuteur.nom }}</td>
          <td>{{ tuteur.numTel }}</td>
          <td>{{ tuteur.email }}</td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<style>

</style>
