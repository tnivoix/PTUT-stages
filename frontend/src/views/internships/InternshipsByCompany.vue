<script setup>
import InternshipsList from "@/components/InternshipsList.vue";
import { reactive, onMounted, ref } from "vue";

const props = defineProps(["id"]);

let data = reactive({
    company: null,
    name:"",
    link: ""
});

var list = ref(null);

onMounted(() => {
    setTimeout(() => {
        list.value.data.link = "internshipsByCompany/" + data.company.id;
        list.value.getInternships();
    }, 150);
})

function getCompany() {
    fetch("/api/entrepriseById/" + props.id)
        .then((response) => {
            if (!response.ok) { // status != 2XX
                throw new Error(response.status);
            }
            return response.json();
        })
        .then((json) => {
            data.company = json;
            data.name=data.company.nom;
        })
        .catch((error) => alert(error));
}
getCompany();
</script>

<template>
    <div id="internshipByCompany">
        <h1>Liste de tous les stages de l'entreprise {{ data.name }}</h1>
        <InternshipsList :link="data.link" ref="list"/>
    </div>
</template>


<style>
</style>
