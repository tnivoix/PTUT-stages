<script setup>
import { reactive, onMounted } from "vue";
import ListDetailAllStage from "@/components/ListDetailAllStage.vue";

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
  fetch("api/allInterships", fetchOptions)
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
        <tr v-for="stage in data.stages" :key="stage.id">
          <td>{{ stage.sujet }}</td>
          <td>{{ stage.anneeEtude }}</td>
          <td>{{ stage.adresse }}</td>
          <td>{{ stage.ville }}</td>
          <td>{{ stage.codePostal }}</td>
          <td>{{ stage.pays }}</td>
          <td>{{ stage.dateDebut }}</td>
          <td>{{ stage.duree }}</td>
          <td> 
            <div v-if="!detail" id="NonDetail"><button id="buttonNonDetail" @click="handlerDetail()">Afficher</button></div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
   <div v-if="detail" id="Detail"><ListDetailAllStage @Revenir="() => handlerDetail()"/></div>


</template>


<style>

</style>
