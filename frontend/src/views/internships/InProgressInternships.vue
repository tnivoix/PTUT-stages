<script setup>
import { reactive, onBeforeMount, onMounted } from "vue";
import InternshipsList from "@/components/InternshipsList.vue";

var link = "inProgressInternships";

const props = defineProps(["id"]);

const emptySJ = {
  soutenance: "2022-07-12",
  jury: "",
};

let data = reactive({
    internship: null,
    ready: false,
    stageEnCours: false,
});

onBeforeMount(() => {
  getInternship();
});

function stageEnCours() {
  var newName = "Étudiant assigné";
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
    fetch("/api/changeInternshipState/" + data.internship.id + "/" + newEtatStage).then(
      () => {
        getInternship();
      }
    );
  }, 200);
}

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
      data.stageEnCours =
        data.internship.etatStage.nom == "Étudiant assigné";
    })
    .catch((error) => alert(error));
}
</script>

<template>
  <h1>Liste de tous les stages en cours</h1>
  <InternshipsList :link="link" />
  <div v-if="data.stageEnCours">
    <label for="anneeEtude" class="form-label">Le jury est :</label>
    <input
      class="form-control"
      required="required"
      type="text"
      v-model="data.emptySJ.jury"
    />
    <button v-if="data.stageEnCours" @click="stageEnCours()">
      Valider le jury et la date de soutenance
    </button>
  </div>
</template>

<style></style>
