<template>
    <div class="form-group">
        <label for="country">Pays :</label>
  <select id="country" class="form-control" @input="$emit('eventSelected', $event.target.value)">
      <option value="" disabled selected>Choisissez un pays</option>
    <option v-for="country in data.countries" :value="country._links.cities.href">
      {{ country.name }}
    </option>
  </select>
  </div>
</template>

<script setup>
import { reactive, onMounted } from "vue";
defineEmits(["eventSelected"]);

let data = reactive({
  countries: [],
});

defineExpose({
  // On expose la méthode 'refresh' pour être utilisée par le parent
  refresh,
});

function refresh() {
  fetch("api/countries")
    .then((response) => {
      if (!response.ok) {
        // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.countries = json._embedded.countries;
    })
    .catch((error) => alert(error));
}

onMounted(refresh);
</script>
