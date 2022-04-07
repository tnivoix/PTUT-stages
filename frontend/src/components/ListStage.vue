<script setup>
/*const props = defineProps({
  stage: {
    type: Array,
    required: true,
  },
});*/



import { reactive, onMounted } from "vue";

let data = reactive({
  stages: [],
});

defineExpose({ // On expose la méthode 'getStage' pour être utilisée par le parent
  getStage,
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


function getStage() {
  fetch("api/stages")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.stages = json._embedded.stages;
    })
    .catch((error) => alert(error));
}

onMounted(getStage);



</script>

<template>
 <div>
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>AnneeEtude</th>
          <th>Adresse</th>
          <th>Ville</th>  
          <th>CodePostal</th>  
          <th>Pays</th>
          <th>Contexte</th>
          <th>Sujet</th>  
          <th>Details</th> 
          <th>Activites</th>
          <th>Competences</th>
          <th>DateDebut</th>  
          <th>Duree</th> 
          <th>Remuneration</th>
          <th>Service</th>
          <th>Horaire</th>  
          <th>Commentaires</th>
          <th>Soutenance</th>
          <th>Jury</th>   
          <th>MaitreDeStage</th> 
          <th>Fonction</th>   
        </tr>
      </thead>
      <tbody>
        <tr v-for="stage in data.stages" :key="stage.id">
          <td>{{ stage.anneeEtude }}</td>
          <td>{{ stage.adresse }}</td>
          <td>{{ stage.ville }}</td>
          <td>{{ stage.CodePostal }}</td>
          <td>{{ stage.pays }}</td>
          <td>{{ stage.contexte }}</td>
          <td>{{ stage.sujet }}</td>
          <td>{{ stage.details }}</td>
          <td>{{ stage.activites }}</td>
          <td>{{ stage.competences }}</td>
          <td>{{ stage.dateDebut }}</td>
          <td>{{ stage.duree }}</td>
          <td>{{ stage.remuneration }}</td>
          <td>{{ stage.service }}</td>
          <td>{{ stage.horaire }}</td>
          <td>{{ stage.commentaires }}</td>
          <td>{{ stage.soutenance }}</td>
          <td>{{ stage.jury }}</td>
          <td>{{ stage.maitreDeStage }}</td>
          <td>{{ stage.fonction }}</td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<style>

</style>
