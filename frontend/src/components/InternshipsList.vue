<script setup>
import { reactive, onMounted } from "vue";
import router from "@/router/index";
import { define } from "mime";

const props = defineProps(["link"]);

let data = reactive({
    stages: [],
    link: props.link
});

defineExpose({
    getInternships,
    data
})

onMounted(
    getInternships,
);

function internshipRedirect(id) {
    router.push("/internship/" + id);
}

function getInternships() {
    if (data.link != "") {
        fetch("/api/" + data.link)
            .then((response) => {
                if (!response.ok) { // status != 2XX
                    throw new Error(response.status);
                }
                return response.json();
            })
            .then((json) => {
                data.stages = json;
            })
            .catch((error) => alert(error));
    }
}
</script>

<template>
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
                    <th>DateDebut</th>
                    <th>Duree</th>
                    <th>Détails</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="stage in data.stages" :key="stage.id">
                    <td>{{ stage.sujet }}</td>
                    <td>{{ stage.anneeEtude }}</td>
                    <td>{{ stage.adresse }}</td>
                    <td>{{ stage.ville }}</td>
                    <td>{{ stage.codePostal }}</td>
                    <td>{{ stage.pays }}</td>
                    <td>{{ stage.dateDebut }}</td>
                    <td>{{ stage.duree }}</td>
                    <td>
                        <button @click="internshipRedirect(stage.id)">Afficher</button>
                    </td>
                </tr>
            </tbody>
        </table>

    </div>
    


</template>


<style>
</style>
