<script setup>
import InternshipsList from "@/components/InternshipsList.vue";
import UserService from "@/services/user.service";
import store from "@/store/index";
import { reactive, onMounted, ref } from "vue";

const props = defineProps(["id"]);

const data = reactive({
  tutor: null,
  name: "",
  link: "",
  allowed: false,
});

var list = ref(null);

onMounted(() => {
  setTimeout(() => {
    if (data.allowed) {
      list.value.data.link = "internshipsByTutor/" + data.tutor.id;
      list.value.getInternships();
    }
  }, 400);
});

function getTutor() {
  fetch("/api/utilisateurById/" + props.id)
    .then((response) => {
      if (!response.ok) {
        // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.tutor = json;
      data.name = data.tutor.nom;
    })
    .catch((error) => alert(error));
}
getTutor();

function test() {
  var resp = false;
  var student = false;
  UserService.getRespBoard().then((response) => {
    if (response.ok) {
      resp = true;
    }
  });
  UserService.getTutorBoard().then((response) => {
    if (response.ok && store.state.auth.user.id == props.id) {
      student = true;
    }
  });
  setTimeout(() => {
    data.allowed = resp || student;
  }, 400);
}

test();
</script>

<template>
  <div v-if="data.allowed" id="internshipByTutor">
    <h1>Liste de tous les stages du tuteur {{ data.name }}</h1>
    <InternshipsList :link="data.link" ref="list" />
  </div>
  <div v-else>Vous n'êtes pas autorisé à accéder à cette page.</div>
</template>

<style></style>
