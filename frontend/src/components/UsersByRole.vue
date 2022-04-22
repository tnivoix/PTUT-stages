<script setup>
import { reactive, onMounted } from "vue";
import router from "@/router/index";

const props = defineProps(["role"]);

let data = reactive({
  users: [],
});

onMounted(getUsers);

function internshipsRedirect(id) {
    router.push("/internshipsBy"+props.role+"/"+id);
}

function getUsers() {
  fetch("/api/all"+props.role+"s")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.users = json;
    })
    .catch((error) => alert(error));
}
</script>

<template>
 <div class="container">
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Identifiant</th>
          <th>Prenom</th>  
          <th>Nom</th> 
          <th>NumTel</th>
          <th>Email</th>
          <th>Stages</th>      
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in data.users" :key="user.id">
          <td>{{ user.identifiant }}</td>
          <td>{{ user.prenom }}</td>
          <td>{{ user.nom }}</td>
          <td>{{ user.numTel }}</td>
          <td>{{ user.email }}</td>
          <td>
            <button @click="internshipsRedirect(user.id)">Stages</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<style>

</style>
