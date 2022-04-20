<template>

  <h2>Formulaire d'inscription entreprise</h2>
  <p>Tous les champs doivent être remplis.</p>
  <div class="container mt-3">
    <form @submit.prevent="addEntreprise">
      <div class="mb-3">
        <label for="secteur" class="form-label">Secteur d'activité :</label>
        <input class="form-control" required="required" v-model="data.entreprise.secteurActivite" />
      </div>
      <div class="mb-3">
        <label for="nom" class="form-label">Nom de l'entreprise :</label>
        <input class="form-control" required="required" v-model="data.entreprise.nom" />
      </div>
      <div class="mb-3">
        <label for="numTel" class="form-label">Numéro de téléphone :</label>
        <input type="tel" pattern="[00-99]{2}[00-99]{2}[00-99]{2}[00-99]{2}[0-99]{2}" class="form-control" required="required" v-model="data.entreprise.numTel" />
        <small>Format: 0123456789</small>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">@email :</label>
        <input class="form-control" required="required" type="email" v-model="data.entreprise.email" />
      </div>
      <button type="submit" class="btn btn-primary">S'inscrire</button>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";


const emptyentreprise = {
  id: "",
  nom: "",
  numTel: "",
  email: "",
  secteurActivite: "",
};

const data = reactive({
  entreprise: {...emptyentreprise},
});

defineExpose({
  data,
})

const emit = defineEmits(['companyAdded',])

function addEntreprise() {
  const options = {
    method: "POST",
    mode: "cors",
    body: JSON.stringify(data.entreprise),
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("/api/entreprises", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      console.log("Company "+data.entreprise.nom+" added");
      data.entreprise = {...emptyentreprise}; // On réinitialise le formulaire
      emit('companyAdded'); // On notifie le parent que le pays a été ajouté
    })
    .catch((error) => alert(error));
}
</script>