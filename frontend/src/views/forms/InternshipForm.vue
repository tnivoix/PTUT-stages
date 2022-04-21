<template>
  <h1>Ajouter un stage</h1>
  <p>Tous les champs doivent être remplis.</p>
  <div class="container mt-3">
    <form @submit.prevent="addInternship">
      <div class="form-group">
        <label for="entreprise">Entreprise : (sélectionnez)</label>
        <select id="selectEntreprise" class="form-control" required="required" v-model="data.internship.entreprise">
          <option disabled value="0">Choisissez une entreprise</option>
          <option v-for="entreprise in data.allEntreprises" :key="entreprise.id" :value="entreprise._links.self.href">
            {{ entreprise.nom }}
          </option>
        </select>
      </div>
      <label class="form-label">Si l'entreprise n'est pas dans la liste, veuillez cliquer afin de créer
        l'entreprise
      </label>
      <button id="togg1" type="button">Créer une nouvelle entreprise</button>
      <div id="d1" hidden>
        <CompanyForm @companyAdded="refreshCompanies" />
      </div>
      <div class="mb-3">
        <label for="anneeEtude" class="form-label">Année d'étude :</label>
        <input class="form-control" required="required" type="number" v-model="data.internship.anneeEtude" />
      </div>
      <div class="mb-3">
        <label for="adresse" class="form-label">Adresse :</label>
        <input class="form-control" required="required" v-model="data.internship.adresse" />
      </div>
      <div class="mb-3">
        <label for="ville" class="form-label">Ville :</label>
        <input class="form-control" required="required" v-model="data.internship.ville" />
      </div>
      <div class="mb-3">
        <label for="codePostal" class="form-label">Code Postal :</label>
        <input class="form-control" required="required" v-model="data.internship.codePostal" />
      </div>
      <div class="mb-3">
        <label for="pays" class="form-label">Pays :</label>
        <input class="form-control" required="required" v-model="data.internship.pays" />
      </div>
      <div class="mb-3">
        <label for="contexte" class="form-label">Contexte :</label>
        <input class="form-control" required="required" v-model="data.internship.contexte" />
      </div>
      <div class="mb-3">
        <label for="sujet" class="form-label">Sujet :</label>
        <input class="form-control" required="required" v-model="data.internship.sujet" />
      </div>
      <div class="mb-3">
        <label for="details" class="form-label">Détails :</label>
        <input class="form-control" required="required" v-model="data.internship.details" />
      </div>
      <div class="mb-3">
        <label for="activites" class="form-label">Activités :</label>
        <input class="form-control" required="required" v-model="data.internship.activites" />
      </div>
      <div class="mb-3">
        <label for="competences" class="form-label">Compétences :</label>
        <input class="form-control" required="required" v-model="data.internship.competences" />
      </div>
      <div class="mb-3">
        <label for="dateDebut" class="form-label">Date de début :</label>
        <input class="form-control" required="required" type="date" v-model="data.internship.dateDebut" />
      </div>
      <div class="mb-3">
        <label for="duree" class="form-label">Durée : (nombre de semaines)</label>
        <input class="form-control" required="required" type="number" v-model="data.internship.duree" />
      </div>
      <div class="mb-3">
        <label for="remuneration" class="form-label">Rémunération :</label>
        <input class="form-control" required="required" type="number" step="0.01"
          v-model="data.internship.remuneration" />
      </div>
      <div class="mb-3">
        <label for="service" class="form-label">Service :</label>
        <input class="form-control" required="required" v-model="data.internship.service" />
      </div>
      <div class="mb-3">
        <label for="horaires" class="form-label">Horaires :</label>
        <input class="form-control" required="required" v-model="data.internship.horaires" />
      </div>
      <div class="mb-3">
        <label for="maitreDeStage" class="form-label">Maitre de stage :</label>
        <input class="form-control" required="required" v-model="data.internship.maitreDeStage" />
      </div>
      <div class="mb-3">
        <label for="fonction" class="form-label">Fontion :</label>
        <input class="form-control" required="required" v-model="data.internship.fonction" />
      </div>
      <div class="mb-3">
        <label for="maitreDeStage" class="form-label">Numéro de téléphone du maitre de stage :</label>
        <input type="tel" pattern="[00-99]{2}[00-99]{2}[00-99]{2}[00-99]{2}[0-99]{2}" class="form-control" required="required" v-model="data.internship.numTel" />
        <small>Format: 0123456789</small>
      </div>
      <div class="mb-3">
        <label for="fonction" class="form-label">email du maitre de stage :</label>
        <input type="email" class="form-control" required="required" v-model="data.internship.email" />
      </div>
      <button type="submit" class="btn btn-primary">Ajouter le stage</button>
      <input type="reset" value="Reset" />
    </form>
  </div>
</template>

<script setup>
import { reactive, onMounted } from "vue";
import CompanyForm from "@/components/CompanyForm.vue";


const emptyInternship = {
  id: "",
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
  remuneration: 0.0,
  service: "",
  horaires: "",
  maitreDeStage: "",
  fonction: "",
  entreprise: "",
  numTel: "",
  email: "",
  etatStage: "http://localhost:8989/api/etatStages/1",
};

const data = reactive({
  allEntreprises: [],
  internship: { ...emptyInternship },
});

function fetchEntreprises() {
  fetch("/api/entreprises")
    .then((response) => response.json())
    .then((json) => {
      data.allEntreprises = json._embedded.entreprises;
    })
    .catch((error) => alert(error));
}

function refreshCompanies() {
  fetchEntreprises();
  document.getElementById("d1").hidden = true;
  setTimeout(function () { document.getElementById("selectEntreprise").value = document.getElementById("selectEntreprise").lastElementChild.value }, 150);
}

function addInternship() {
  const options = {
    method: "POST",
    body: JSON.stringify(data.internship),
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("/api/stages", options)
    .then((response) => {
      if (!response.ok) {
        // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      console.log("Internship "+data.internship.id+" added");
      data.internship = { ...emptyInternship }; // On réinitialise le formulaire
    })
    .catch((error) => {
      console.log(error);
      alert(error);
    });
}

function initEntrepriseForm() {
  let togg1 = document.getElementById("togg1");
  let d1 = document.getElementById("d1");

  togg1.addEventListener("click", () => {
    d1.hidden = !d1.hidden;
  })
}

onMounted(() => {
  fetchEntreprises(); // On récupère les entreprises (pour le sélecteur d'entreprise)
  initEntrepriseForm();
});
</script>
