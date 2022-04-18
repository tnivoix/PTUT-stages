<script setup>
import InternshipsList from "@/components/InternshipsList.vue";
import { reactive, onMounted } from "vue";

const props = defineProps(["id"]);

let data = reactive({
    student: null,
    link:"",
    ready:false
});

onMounted(() => {
    getStudent();
});

function getStudent() {
    fetch("/api/utilisateurById/" + props.id)
        .then((response) => {
            if (!response.ok) { // status != 2XX
                throw new Error(response.status);
            }
            return response.json();
        })
        .then((json) => {
            data.student = json;
            data.ready = true;
            data.link = "internshipsByStudent/" + data.student.id;
            var el = document.createElement("div");
            el.innerHTML = "<InternshipsList :link=\"data.link\" />";
            //document.getElementById("internshipByStudent").appendChild(el);
        })
        .catch((error) => alert(error));
}
</script>

<template>
    <div id="internshipByStudent">
        <h1 v-if="data.ready">Liste de tous les stages de l'étudiant {{ data.student.nom }}</h1>
    </div>
</template>


<style>
</style>
