<template>
  <h2>Ajouter un stage</h2>
  <p>Tous les champs doivent être remplis.</p>
  <div class="container mt-3">
    <form @submit.prevent="addInternship">
      <div class="mb-3">
        <label for="anneeEtude" class="form-label">Année d'étude :</label>
        <input
          class="form-control"
          required="required"
          type="number"
          v-model="data.internship.anneeEtude"
        />
      </div>
      <div class="mb-3">
        <label for="adresse" class="form-label">Adresse :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.adresse"
        />
      </div>
      <div class="mb-3">
        <label for="ville" class="form-label">Ville :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.ville"
        />
      </div>
      <div class="mb-3">
        <label for="codePostal" class="form-label">Code Postal :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.codePostal"
        />
      </div>
      <div class="mb-3">
        <label for="pays" class="form-label">Pays :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.pays"
        />
      </div>
      <div class="mb-3">
        <label for="contexte" class="form-label">Contexte :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.contexte"
        />
      </div>
      <div class="mb-3">
        <label for="sujet" class="form-label">Sujet :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.sujet"
        />
      </div>
      <div class="mb-3">
        <label for="details" class="form-label">Détails :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.details"
        />
      </div>
      <div class="mb-3">
        <label for="activites" class="form-label">Activités :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.activites"
        />
      </div>
      <div class="mb-3">
        <label for="competences" class="form-label">Compétences :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.competences"
        />
      </div>
      <div class="mb-3">
        <label for="dateDebut" class="form-label">Date de début :</label>
        <input
          class="form-control"
          required="required"
          type="date"
          v-model="data.internship.dateDebut"
        />
      </div>
      <div class="mb-3">
        <label for="duree" class="form-label">Durée : (nombre de semaines)</label>
        <input
          class="form-control"
          required="required"
          type="number"
          v-model="data.internship.duree"
        />
      </div>
      <div class="mb-3">
        <label for="remuneration" class="form-label">Rémunération :</label>
        <input
          class="form-control"
          required="required"
          type="number" 
          step="0.01"
          v-model="data.internship.remuneration"
        />
      </div>
      <div class="mb-3">
        <label for="service" class="form-label">Service :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.service"
        />
      </div>
      <div class="mb-3">
        <label for="horaires" class="form-label">Horaires :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.horaires"
        />
      </div>
      <div class="mb-3">
        <label for="maitreDeStage" class="form-label">Maitre de stage :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.maitreDeStage"
        />
      </div>
      <div class="mb-3">
        <label for="fonction" class="form-label">Fonction :</label>
        <input
          class="form-control"
          required="required"
          v-model="data.internship.fonction"
        />
      </div>
      <button type="submit" class="btn btn-primary">Ajouter le stage</button>
      <input type="reset" value="Reset" />
    </form>
  </div>
</template>

<script setup>
import {reactive} from "vue";

const emptyInternship = {
  anneeEtude: 0,
  adresse: "",
  ville: "",
  codePostal: "",
  pays: "",
  contexte: "",
  sujet: "",
  details: "",
  activites: "",
  competences: "",
  dateDebut: "2022-07-12",
  duree: 0,
  remuneration: 0.00,
  service: "",
  horaires: "",
  maitreDeStage: "",
  fonction: "",
  entreprise: "http://localhost:8989/api/entreprises/1",
  etatStage: "http://localhost:8989/api/etatStages/1",
};

const data = reactive({
  internship: {...emptyInternship},
});

defineExpose({
  data,
});

const emit = defineEmits(["internshipAdded",]);

function addInternship() {
  const options = {
    method: "POST",
    body: JSON.stringify(data.internship),
    headers: {
      "Content-Type": "application/json",
    },
  };
  console
  fetch("api/stages", options)
    .then((response) => {
      if (!response.ok) {
        // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.internship = {...emptyInternship}; // On réinitialise le formulaire
      emit("internshipAdded", json); // On notifie le parent que le stage a été ajouté

    })
    .catch((error) => {
      console.log(error)
      alert(error)});
}


</script>