<script setup>
import { reactive, onMounted } from "vue";




let data = reactive({
  stages: [],
});

defineExpose({ // On expose la méthode 'getStage' pour être utilisée par le parent
  getStage,
})

function getStage() {
  const fetchOptions = {method: "GET"};
  fetch("api/freeInterships", fetchOptions)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.stages = json;
    })
    .catch((error) => alert(error));
}


onMounted(
  getStage,
  );

getStage();
</script>

<template>
 <div class="container">
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Sujet</th> 
          <th>AnneeEtude</th>
          <th>Adresse</th>
          <th>Ville</th>  
          <th>CodePostal</th>  
          <th>Pays</th>
          <th>Contexte</th> 
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
          <th>Fermer</th>  
        </tr>
      </thead>
      <tbody>
        <tr v-for="freeInterships in data.stages" :key="freeInterships.id">
          <td>{{ freeInterships.sujet }}</td>
          <td>{{ freeInterships.anneeEtude }}</td>
          <td>{{ freeInterships.adresse }}</td>
          <td>{{ freeInterships.ville }}</td>
          <td>{{ freeInterships.codePostal }}</td>
          <td>{{ freeInterships.pays }}</td>
          <td>{{ freeInterships.contexte }}</td>
          <td>{{ freeInterships.details }}</td>
          <td>{{ freeInterships.activites }}</td>
          <td>{{ freeInterships.competences }}</td>
          <td>{{ freeInterships.dateDebut }}</td>
          <td>{{ freeInterships.duree }}</td>
          <td>{{ freeInterships.remuneration }}</td>
          <td>{{ freeInterships.service }}</td>
          <td>{{ freeInterships.horaire }}</td>
          <td>{{ freeInterships.commentaires }}</td>
          <td>{{ freeInterships.soutenance }}</td>
          <td>{{ freeInterships.jury }}</td>
          <td>{{ freeInterships.maitreDeStage }}</td>
          <td>{{ freeInterships.fonction }}</td>
          <td>
              <div id="listDetail"><button id="buttonListDetail" @click="$emit('Revenir')">Fermer</button>
          </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<style>

</style>