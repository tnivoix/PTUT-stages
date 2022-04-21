<script setup>
import { reactive, onBeforeMount } from "vue";

const props = defineProps(["id"]);

let data = reactive({
  internship: null,
  ready: false,
  validate: false,
});
onBeforeMount(() => {
  getInternship();
});

function getInternship() {
  fetch("/api/internshipById/" + props.id)
    .then((response) => {
      if (!response.ok) {
        // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.internship = json;
      data.ready = true;
      data.validate =
        data.internship.etatStage.nom == "Proposition en attente de validation";
    })
    .catch((error) => alert(error));
}

function validate() {
  var newName = "Proposition validée";
  var newEtatStage = null;
  fetch("/api/etatStageByNom/" + newName)
    .then((response) => {
      if (!response.ok) {
        // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      newEtatStage = json.id;
    })
    .catch((error) => alert(error));
  setTimeout(() => {
    fetch("/api/changeInternshipState/" + data.internship.id + "/" + newEtatStage)
      .then(() => {
        getInternship();
      });
  }, 200);
}
</script>

<template>
  <div v-if="data.ready">
    <br>
    <h1>Stage n°{{ data.internship.id }}</h1>
    <div id="info" class="container">
      <!--<table class="table table-bordered table-sm table-hover">
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
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ data.internship.sujet }}</td>
            <td>{{ data.internship.anneeEtude }}</td>
            <td>{{ data.internship.adresse }}</td>
            <td>{{ data.internship.ville }}</td>
            <td>{{ data.internship.codePostal }}</td>
            <td>{{ data.internship.pays }}</td>
            <td>{{ data.internship.contexte }}</td>
            <td>{{ data.internship.details }}</td>
            <td>{{ data.internship.activites }}</td>
            <td>{{ data.internship.competences }}</td>
            <td>{{ data.internship.dateDebut }}</td>
            <td>{{ data.internship.duree }}</td>
            <td>{{ data.internship.remuneration }}</td>
            <td>{{ data.internship.service }}</td>
            <td>{{ data.internship.horaire }}</td>
            <td>{{ data.internship.commentaires }}</td>
            <td>{{ data.internship.soutenance }}</td>
            <td>{{ data.internship.jury }}</td>
            <td>{{ data.internship.maitreDeStage }}</td>
            <td>{{ data.internship.fonction }}</td>
          </tr>
        </tbody>
      </table>-->
      <br>
      <h2>Sujet du stage : {{ data.internship.sujet }}</h2>
      <p>Date du stage : {{ data.internship.dateDebut }}, d'une durée de {{ data.internship.duree }} semaines</p>
      <br>
      <h3>Etudiant : </h3>
      <p>Année d'étude : {{ data.internship.anneeEtude }}e année</p>
      <br>
      <h3>Entreprise : </h3>
      <p>Coordonnées : </p>
      <p>Secteur : </p>
      <p>Adresse du stage : {{ data.internship.adresse }}, {{ data.internship.codePostal }} {{ data.internship.ville }}, {{ data.internship.pays }}</p>
      <br>
      <h3>Maitre de Stage : </h3>
      <p>Fonction : {{ data.internship.fonction }}</p>
      <p>Coordonnées : {{ data.internship.numTel }}, {{ data.internship.email }}</p>
      <br>
      <h3>Stage : </h3>
      <p>Service de l'entreprise : {{ data.internship.service }}</p>
      <p>Contexte : {{ data.internship.contexte }}</p>
      <p>Détails : {{ data.internship.details }}</p>
      <p>Activités du stagiaire : {{ data.internship.activites }}</p>
      <p>Compétences requises : {{ data.internship.competences }}</p>
      <p>Rémunération : {{ data.internship.remuneration }} euros/semaine</p>
      <br>
      <h3>Evaluation : </h3>
      <p>Date soutenance : {{ data.internship.soutenance }}</p>
      <p>Jury : {{ data.internship.jury }}</p>
    </div>
    <button v-if="data.validate" @click="validate()">
      Valider la proposition de stage
    </button>
  </div>
</template>

<style>
div#info{
  text-align: left;
}
</style>
