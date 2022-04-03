<script setup>
/*const props = defineProps({
  utilisateur: {
    type: Array,
    required: true,
  },
});*/


import { reactive, onMounted } from "vue";

let data = reactive({
  utilisateurs: [],
});

defineExpose({ // On expose la méthode 'getutilisateurs' pour être utilisée par le parent
  getUtilisateurs,
})

// On définit les événements générés par le composant
//const emit = defineEmits(['countryEdited',])

/*function editCountry(country) {
  emit('countryEdited', country); // On notifie le parent qu'il faut modifier le pays
}

function deleteCountry(country) {
  console.log(country);
  const options = {
    method: "DELETE",
  };
  fetch(country._links.self.href, options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      refresh();
    })
    .catch((error) => alert(error));  
}
*/


function getUtilisateurs() {
  fetch("api/utilisateurs")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.utilisateurs = json._embedded.utilisateurs;
    })
    .catch((error) => alert(error));
}

onMounted(getUtilisateurs);






</script>

<template>
 <div>
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
        <tr v-for="utilisateur in data.utilisateurs" :key="utilisateur.id">
          <td>{{ utilisateur.identifiant }}</td>
          <td>{{ utilisateur.motDePasse }}</td>
          <td>{{ utilisateur.prenom }}</td>
          <td>{{ utilisateur.nom }}</td>
          <td>{{ utilisateur.numTel }}</td>
          <td>{{ utilisateur.email }}</td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<style>

</style>
