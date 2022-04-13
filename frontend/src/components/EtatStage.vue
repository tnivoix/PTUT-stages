<script setup>
import { reactive, onMounted } from "vue";
let data = reactive({
  etatStages: [],
  etatUnStage: []
});

defineExpose({ // On expose la méthode 'getStage' pour être utilisée par le parent
  getEtatStage,
  getIdStage,
})

function getEtatStage() {
  const fetchOptions = {method: "GET"};
  fetch("api/etatStages", fetchOptions)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.etatStages = json._embedded.etatStages;
    })
    .catch((error) => alert(error));
}



function getIdStage(id) {
  const fetchOptions = {method: "GET"};
  fetch("api/etatStages/"+id, fetchOptions)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.etatUnStage = [json];
    })
    .catch((error) => alert(error));
}


function getIdFormStage() {
  let resultat = document.getElementById('select').value;
  const fetchOptions = {method: "GET"};
  fetch(resultat, fetchOptions)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.etatUnStage = [json];
    })
    .catch((error) => alert(error));
}



onMounted(
  getEtatStage,
  getIdStage
  );

getEtatStage();
getIdStage(1);

</script>

<template>
<div>
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Nom</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="etatStage in data.etatStages" :key="etatStage.id"> 
           <td>{{ etatStage.nom }}</td>
        </tr>
      </tbody>
    </table>

     <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Nom</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="etatStage in data.etatUnStage" :key="etatStage.id"> 
           <td>{{ etatStage.nom }}</td>
        </tr>
      </tbody>
    </table>
</div>
<div>
 <label for="etatStage">Etat du stage :</label>
            <select class="form-control" id="select" @input="getIdFormStage">
              
              <option disabled value="0">Choisissez un état</option>
              <option
                v-for="etatStage in data.etatStages" :key="etatStage.id"
                :value="etatStage._links.self.href"
              >
                {{ etatStage.nom }}
              </option>
            </select>
</div>

</template>

<style>

</style>
