<script setup>
import InternshipsList from "@/components/InternshipsList.vue";
import UserService from '@/services/user.service';
import { reactive, onMounted, ref } from "vue";

const props = defineProps(["id"]);

const data = reactive({
    company: null,
    name: "",
    link: "",
    allowed: false
});

var list = ref(null);

onMounted(() => {
    setTimeout(() => {
        list.value.data.link = "internshipsByCompany/" + data.company.id;
        list.value.getInternships();
    }, 400);
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
            data.name = data.company.nom;
        })
        .catch((error) => alert(error));
}
getCompany();

function test() {
    UserService.getRespBoard().then(
        response => {
            if (response.ok) {
                data.allowed = true;
            }
        }
    )
}

test();
</script>

<template>
    <div v-if="data.allowed" id="internshipByCompany">
        <h1>Liste de tous les stages de l'entreprise {{ data.name }}</h1>
        <InternshipsList :link="data.link" ref="list" />
    </div>
    <div v-else>
        Vous n'êtes pas autorisé à accéder à cette page.
    </div>
</template>


<style>
</style>
