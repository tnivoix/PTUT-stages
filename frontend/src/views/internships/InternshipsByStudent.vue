<script setup>
import InternshipsList from "@/components/InternshipsList.vue";
import { reactive, onMounted, ref } from "vue";

const props = defineProps(["id"]);

let data = reactive({
    student: null,
    name:"",
    link: ""
});

var list = ref(null);

onMounted(() => {
    setTimeout(() => {
        list.value.data.link = "internshipsByStudent/" + data.student.id;
        list.value.getInternships();
    }, 150);
})

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
            data.name=data.student.nom;
        })
        .catch((error) => alert(error));
}
getStudent();
</script>

<template>
    <div id="internshipByStudent">
        <h1>Liste de tous les stages de l'étudiant {{ data.name }}</h1>
        <InternshipsList :link="data.link" ref="list"/>
    </div>
</template>


<style>
</style>
