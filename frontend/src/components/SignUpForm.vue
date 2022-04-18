<template>

  <h2>Formulaire d'inscription</h2>
  <p>Tous les champs doivent être remplis.</p>
  <div class="container mt-3">
    <form @submit.prevent="addUtilisateur">
      <div class="mb-3">
        <select id="roleSelected" required class="form-control" v-model="data.utilisateur.role">
          <option selected disabled hidden value="">Choisissez un rôle</option>
          <option
            v-for="role in data.allRoles"
            :key="role.id"
            :value="role._links.self.href"
          >
          {{ role.nom }}
          </option>
        </select>
      </div>
      <div class="mb-3">
        <label for="prenom" class="form-label">Prénom :</label>
        <input class="form-control" required="required" v-model="data.utilisateur.prenom" />
      </div>
      <div class="mb-3">
        <label for="nom" class="form-label">Nom de famille :</label>
        <input class="form-control" required="required" v-model="data.utilisateur.nom" />
      </div>
      <div class="mb-3">
        <label for="numTel" class="form-label">Numéro de téléphone :</label>
        <input type="tel" pattern="[00-99]{2} [00-99]{2} [00-99]{2} [00-99]{2} [0-99]{2}" class="form-control" required="required" v-model="data.utilisateur.numTel" />
        <small>Format: 05 65 33 76 82</small>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">@email :</label>
        <input class="form-control" required="required" type="email" v-model="data.utilisateur.email" />
      </div>
      <div class="mb-3 mt-3">
        <label for="identifiant" class="form-label">Identifiant :</label>
        <input class="form-control" required="required" v-model="data.utilisateur.identifiant" />
      </div>
      <div class="mb-3">
        <label for="motDePasse" class="form-label">Mot de passe :</label>
        <input type="password" required="required" class="form-control" v-model="data.utilisateur.motDePasse" />
      </div>
      <button type="submit" class="btn btn-primary">S'inscrire</button>
    </form>
  </div>
</template>

<script setup>
import { reactive, onMounted } from "vue";


const emptyUtilisateur = {
  role: "",
  id: "",
  prenom: "",
  nom: "",
  numTel: "",
  email: "",
  identifiant: "",
  motDePasse: "",
};

const data = reactive({
  utilisateur: {...emptyUtilisateur},
  allRoles: [],
});

defineExpose({
  data,
})


const emit = defineEmits(['utilisateurAdded',])

function addUtilisateur() {
  const options = {
    method: "POST",
    mode: "cors",
    body: JSON.stringify(data.utilisateur),
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("/api/utilisateurs", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.utilisateur = {...emptyUtilisateur}; // On réinitialise le formulaire
      emit('utilisateurAdded', json); // On notifie le parent que l'utilisateur a été ajouté
    })
    .catch((error) => alert(error));
}

// Utilise l'API REST auto-générée pour avoir les roles
function fetchRoles() {
  fetch("/api/roles")
    .then((response) => response.json())
    .then((json) => {
      data.allRoles = json._embedded.roles;
    })
    .catch((error) => console.log());
}


// Au chargement du composant
onMounted(() => {
  fetchRoles(); // On récupère les roles
});
</script>