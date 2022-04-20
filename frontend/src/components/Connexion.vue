<template>
  <h2>Connexion Utilisateur</h2>
  <p>Tous les champs doivent être remplis.</p>
  <div class="container mt-3">
    <form @submit.prevent="nouvelleConnexion()">
      <div class="mb-3">
        <label for="identifiant" class="form-label">Identifiant :</label>
        <input class="form-control" required="required" v-model="identifiant" />
      </div>
      <div class="mb-3">
        <label for="motDePasse" class="form-label">Mot de passe :</label>
        <input type="password" class="form-control" required="required" v-model="motDePasse" />
      </div>
      <button type="submit" class="btn btn-primary">Se connecter</button>
    </form>
  </div>
</template>

<script setup>
import {ref} from "vue";
var identifiant =ref("");
var motDePasse =ref("");

const emit = defineEmits(['nouvelleConnexion',])

function nouvelleConnexion() {
  console.log(identifiant.value);
  console.log(motDePasse.value);
  fetch("/api/utilisateurByIdentifiant/"+identifiant.value)
    .then((response) => response.json())
    .then((json) => {
      console.log(json)
    })
    .catch((error) => console.log());
}

</script>