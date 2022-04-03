<script setup>
/*const props = defineProps({
  entreprise: {
    type: Array,
    required: true,
  },
});*/





import { reactive, onMounted } from "vue";

let data = reactive({
  entreprises: [],
});

defineExpose({ // On expose la méthode 'getEntreprises' pour être utilisée par le parent
  getEntreprises,
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


function getEntreprises() {
  fetch("api/entreprises")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.entreprises = json._embedded.entreprises;
    })
    .catch((error) => alert(error));
}

onMounted(getEntreprises);





</script>

<template>
 <div>
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
          <td>{{ entreprise.name }}</td>
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
