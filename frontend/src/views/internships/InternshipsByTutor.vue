<script setup>
import InternshipsList from "@/components/InternshipsList.vue";
import { reactive, onMounted, ref } from "vue";

const props = defineProps(["id"]);

let data = reactive({
    tutor: null,
    name:"",
    link: ""
});

var list = ref(null);

onMounted(() => {
    setTimeout(() => {
        list.value.data.link = "internshipsByTutor/" + data.tutor.id;
        list.value.getInternships();
    }, 150);
})

function getTutor() {
    fetch("/api/utilisateurById/" + props.id)
        .then((response) => {
            if (!response.ok) { // status != 2XX
                throw new Error(response.status);
            }
            return response.json();
        })
        .then((json) => {
            data.tutor = json;
            data.name=data.tutor.nom;
        })
        .catch((error) => alert(error));
}
getTutor();
</script>

<template>
    <div id="internshipByTutor">
        <h1>Liste de tous les stages du tuteur {{ data.name }}</h1>
        <InternshipsList :link="data.link" ref="list"/>
    </div>
</template>


<style>
</style>
