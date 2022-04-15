<script setup>
import { reactive, onMounted } from "vue";
import ListDetailStageDisponible from "@/components/ListDetailStageDisponible.vue";

import {ref} from "vue";

const detail = ref(false);

function handlerDetail(){
  detail.value=!detail.value;
}

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
          <th>DateDebut</th>  
          <th>Duree</th> 
          <th> Détail </th>  
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
          <td>{{ freeInterships.dateDebut }}</td>
          <td>{{ freeInterships.duree }}</td>
          <td> 
            <div v-if="!detail" id="NonDetail"><button id="buttonNonDetail" @click="handlerDetail()">Afficher</button></div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <div v-if="detail" id="Detail"><ListDetailStageDisponible @Revenir="() => handlerDetail()"/></div>
</template>


<style>

</style>
