<script setup>
import { reactive, onBeforeMount } from "vue";

const props = defineProps(["id"]);

let data = reactive({
    internship: null,
    ready:false
});
onBeforeMount(() => {
    getInternship();
});

function getInternship() {
    fetch("/api/internshipById/" + props.id)
        .then((response) => {
            if (!response.ok) { // status != 2XX
                throw new Error(response.status);
            }
            return response.json();
        })
        .then((json) => {
            data.internship = json;
            data.ready=true;
        })
        .catch((error) => alert(error));
}
</script>

<template>
    <div v-if="data.ready">
        <h1>Stage {{ data.internship.id }}</h1>
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
            </table>
        </div>
    </div>
</template>


<style>
</style>