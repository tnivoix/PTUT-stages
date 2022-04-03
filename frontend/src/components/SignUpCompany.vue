<template>

  <h2>Formulaire d'inscription entreprise</h2>
  <p>Tous les champs doivent être remplis.</p>
  <div class="container mt-3">
    <form @submit.prevent="addEntreprise">
      <div class="mb-3">
        <label for="secteur" class="form-label">Secteur d'activité :</label>
        <input class="form-control" required="required" v-model="data.entreprise.secteur" />
      </div>
      <div class="mb-3">
        <label for="nom" class="form-label">Nom de l'entreprise :</label>
        <input class="form-control" required="required" v-model="data.entreprise.nom" />
      </div>
      <div class="mb-3">
        <label for="numTel" class="form-label">Numéro de téléphone :</label>
        <input type="tel" class="form-control" required="required" v-model="data.entreprise.numTel" />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">@email :</label>
        <input class="form-control" required="required" type="email" v-model="data.entreprise.email" />
      </div>
      <div class="mb-3 mt-3">
        <label for="login" class="form-label">Description de l'entreprise :</label>
        <textarea class="form-control" required="required" v-model="data.entreprise.description"/>
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
  description: "",
  secteur: "",
};

const data = reactive({
  entreprise: {...emptyentreprise},
});

defineExpose({
  data,
})


const emit = defineEmits(['entrepriseAdded',])

function addEntreprise() {
  const options = {
    method: "POST",
    mode: "cors",
    body: JSON.stringify(data.entreprise),
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("api/companies", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.entreprise = {...emptyentreprise}; // On réinitialise le formulaire
      emit('entrepriseAdded', json); // On notifie le parent que le pays a été ajouté
    })
    .catch((error) => alert(error));
}
</script>