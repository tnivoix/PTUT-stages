<template>
  <div class="home">
    <h2>Villes dans chaque pays</h2>
    <CountrySelect 
    @eventSelected="handlerSelected"/>
    <hr>
    <CityList v-bind:cities="data.allCities"
    :withCountry="false" />
  </div>
</template>

<script setup>
// @ is an alias to /src
import CountrySelect from "@/components/CountrySelect.vue";
import CityList from "@/components/CityList.vue";
import { reactive } from "vue";

const data = reactive({
  allCities: []
});

function handlerSelected(link) {
  // Utilise l'API ad-hoc pour avoir le pays de chaque ville
  fetch(link)
    .then((response) => response.json())
    .then((json) => {
      data.allCities = json._embedded.cities;
      console.log(data.allCities);
    })
    .catch((error) => alert(error));
}
</script>
