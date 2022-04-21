<script setup>
import { reactive, onBeforeMount, onMounted } from "vue";

const props = defineProps(["id"]);

let data = reactive({
  internship: null,
  ready: false,
  validate: false,
  assign: false,
  students: [],
  tutors : [],
  studentAssigne: false,
  tutorAssigne: false,
});


onBeforeMount(() => {
  getInternship();
  getInternshipAssign();
});

onMounted(() => {
  getStudents();
  getTutors();
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

function getInternshipAssign() {
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
      data.assign =
      data.internship.etatStage.nom == "Proposition validée";
      console.log(json);
      console.log(data.assign);
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
      data.students =json;
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
      data.tutors =json;
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
  }, 200);
}

function assign() {
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
        getInternshipAssign();
      });
  }, 200);
}

function fetchStudent(){
   let etudiantId = document.getElementById("etudiantSelector").value;
  fetch("/api/addUser" + etudiantId )
    .then((response) => response.json())
    .then((json) => {
      data.studentAssigne = json;
    })
    .catch((error) => console.log());
    
}

function fetchSTutor(){
   let tutorId = document.getElementById("tuteurSelector").value;
  fetch("/api/addUser" + tutorId )
    .then((response) => response.json())
    .then((json) => {
      data.tutorAssigne = json;
    })
    .catch((error) => console.log());
    
}

</script>

<template>
  <div v-if="data.ready">
    <h1>Stage n°{{ data.internship.id }}</h1>
    <div class="container">
      <table class="table table-bordered table-sm table-hover">
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
            <th>Nom Etudiant</th>
            <th>Nom Tuteur</th>
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
            <td v-if="data.studentAssigne">{{ data.students.id }}</td>
            <td v-if="data.tutorAssigne">{{ data.tutors.id}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <button v-if="data.validate" @click="validate()">
      Valider la proposition de stage
    </button>

    <div v-if="data.assign">
      <select id="etudiantSelector" @input="fetchStudent">
        <option disabled value="0">Choisissez un etudiant</option>
              <option
                v-for="student in data.students"
                :key="student.id"
                :value="student.id"
                >
              {{student.nom }}
              </option>
      </select>
      <select id="tuteurSelector" @input="fetchTutor">
          <option disabled value="0">Choisissez un tuteur</option>
              <option
                v-for="tutor in data.tutors"
                :key="tutor.id"
                :value="tutor.id"
                >
              {{tutor.nom }}
              </option>
      </select>
      

      <button  @click="assign()">
              Assigner l'étudiant et le tuteur
      </button>
      </div>


    
  </div>
</template>

<style>
</style>
