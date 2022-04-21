<script setup>
import InternshipsList from "@/components/InternshipsList.vue";
import UserService from '@/services/user.service';
import { reactive } from 'vue';

var link = "freeInternships";

const data = reactive({
    allowed: false
})

function test() {
    var resp = false;
    var student = false;
    UserService.getRespBoard().then(
        response => {
            if (response.ok) {
                resp = true;
            }
        }
    )
    UserService.getStudentBoard().then(
        response => {
            if (response.ok) {
                student = true;
            }
        }
    )
    setTimeout(() => {
        data.allowed = resp || student;
    }, 400);
}

test();
</script>

<template>
    <div v-if="data.allowed">
        <h1>Liste de tous les stages disponibles</h1>
        <InternshipsList :link="link" />
    </div>
    <div v-else>
        Vous n'êtes pas autorisé à accéder à cette page.
    </div>
</template>


<style>
</style>