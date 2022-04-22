<script setup>
import { reactive, onBeforeMount, onMounted } from "vue";
import UserService from '@/services/user.service';

const props = defineProps(["id"]);

let data = reactive({
  internship: null,
  ready: false,
  validate: false,
  assign: false,
  students: [],
  tutors: [],
  studentAssigne: false,
  tutorAssigne: false,
  tuteur: '',
  etudiant: '',
  stageEnCours: false,
  soutenance: null,
  jury: null,
  resp: false
});

function test() {
  UserService.getRespBoard().then(
    response => {
      if (response.ok) {
        data.resp = true;
      }
    }
  )
}

test();

onBeforeMount(() => {
  getInternship();
});

onMounted(() => {
  getStudents();
  getTutors();
});

function addJS() {
  var toSend = [data.soutenance.toString(), data.jury];
  const options = {
    method: "PATCH",
    body: JSON.stringify(toSend),
    headers: {
      "Content-Type": "application/json",
    },
  };
  console.log(options);
  fetch("/api/changeSoutenanceAndJury/" + data.internship.id, options)
    .then(() => {
      changeState("Soutenance planifiée");
      setTimeout(() => {
        getInternship();
      }, 400);
    })
    .catch((error) => {
      console.log(error);
      alert(error);
    });
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
      getUsers();
      data.ready = true;
      data.validate = data.internship.etatStage.nom == "Proposition en attente de validation";
      data.stageEnCours = data.internship.etatStage.nom == "Étudiant assigné";
      data.assign = data.internship.etatStage.nom == "Proposition validée";
    })
    .catch((error) => alert(error));
}

function getStudents() {
  fetch("/api/allStudents")
    .then((response) => {
      if (!response.ok) {
        // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.students = json;
    })
    .catch((error) => alert(error));
}

function getTutors() {
  fetch("/api/allTutors")
    .then((response) => {
      if (!response.ok) {
        // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.tutors = json;
    })
    .catch((error) => alert(error));
}

function changeState(newName) {
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
      newEtatStage = json;
    })
    .catch((error) => alert(error));
  setTimeout(() => {
    const options = {
      method: "PATCH",
      body: JSON.stringify(newEtatStage),
      headers: {
        "Content-Type": "application/json",
      },
    };
    fetch("/api/changeInternshipState/" + data.internship.id, options)
      .then(() => {
        getInternship();
      });
  }, 400);
}

function assign() {
  let etudiantId = document.getElementById("etudiantSelector").value;
  let tutorId = document.getElementById("tuteurSelector").value;
  const options = {
    method: "PATCH",
    body: JSON.stringify(etudiantId),
    headers: {
      "Content-Type": "application/json",
    },
  };
  const options2 = {
    method: "PATCH",
    body: JSON.stringify(tutorId),
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("/api/addUser/" + data.internship.id, options)
    .then(() => {});
  fetch("/api/addUser/" + data.internship.id, options2)
    .then(() => {
      getInternship();
    });
    changeState("Étudiant assigné")
}

function getUsers() {
  data.internship.utilisateurs.forEach(user => {
    if (user.role.nom == 'ROLE_ETUDIANT') {
      data.etudiant = user;
    } else if (user.role.nom == 'ROLE_TUTEUR') {
      data.tuteur = user;
    }
  });

}
</script>

<template>
  <div v-if="data.ready">
    <h1>Stage n°{{ data.internship.id }}</h1>
    <div id="info" class="container">
      <h2>Sujet du stage : {{ data.internship.sujet }}</h2>
      <p>Tuteur de stage : {{ data.tuteur.prenom }} {{ data.tuteur.nom }}</p>
      <p>Date du stage : {{ data.internship.dateDebut }}, d'une durée de {{ data.internship.duree }} semaines</p>
      <br>
      <h3>Etudiant : {{ data.etudiant.prenom }} {{ data.etudiant.nom }}</h3>
      <p>Année d'étude : {{ data.internship.anneeEtude }}e année</p>
      <br>
      <h3>Entreprise : {{ data.internship.entreprise.nom }}</h3>
      <p>Coordonnées : {{ data.internship.entreprise.numTel }}, {{ data.internship.entreprise.email }}</p>
      <p>Secteur : {{ data.internship.entreprise.secteurActivite }}</p>
      <p>Adresse du stage : {{ data.internship.adresse }}, {{ data.internship.codePostal }} {{ data.internship.ville }},
        {{ data.internship.pays }}</p>
      <br>
      <h3>Maitre de Stage : {{ data.internship.maitreDeStage }}</h3>
      <p>Fonction : {{ data.internship.fonction }}</p>
      <p>Coordonnées : {{ data.internship.numTel }}, {{ data.internship.email }}</p>
      <br>
      <h3>Stage : </h3>
      <p>Service de l'entreprise : {{ data.internship.service }}</p>
      <p>Contexte : {{ data.internship.contexte }}</p>
      <p>Détails : {{ data.internship.details }}</p>
      <p>Activités du stagiaire : {{ data.internship.activites }}</p>
      <p>Compétences requises : {{ data.internship.competences }}</p>
      <p>Rémunération : {{ data.internship.remuneration }} euros/heure</p>
      <br>
      <h3>Evaluation : </h3>
      <p>Date soutenance : {{ data.internship.soutenance }}</p>
      <p>Jury : {{ data.internship.jury }}</p>
    </div>
    <button v-if="data.validate && data.resp" @click="changeState('Proposition validée')">
      Valider la proposition de stage
    </button>
    <div v-if="data.assign && data.resp">
      <select id="etudiantSelector">
        <option disabled value="0">Choisissez un etudiant</option>
        <option v-for="student in data.students" :key="student.id" :value="student.id">
          {{ student.nom }}
        </option>
      </select>
      <select id="tuteurSelector">
        <option disabled value="0">Choisissez un tuteur</option>
        <option v-for="tutor in data.tutors" :key="tutor.id" :value="tutor.id">
          {{ tutor.nom }}
        </option>
      </select>
      <button @click="assign()">
        Assigner l'étudiant et le tuteur
      </button>
    </div>
    <div v-if="data.stageEnCours && data.resp">
      <form @submit.prevent="addJS">
        <div>
          <label for="jury" class="form-label">Le jury est :</label>
          <input class="form-control" type="text" required="required" v-model="data.jury" />

          <label for="soutenance" class="form-label">Date de la soutenance :</label>
          <input class="form-control" required="required" type="Date" v-model="data.soutenance" />

          <button type="submit" class="btn btn-primary">
            Valider le jury et la date de soutenance
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<style>
div#info {
  text-align: left;
}
</style>
