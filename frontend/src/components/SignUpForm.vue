<template>
<!-- 
identifiant = identifiant; motDePasse = mot de passe; ...

comment récuperer les roles dans la base de donnée ?

changer le "addCountry"

confimation de mot de passe
-->
  <h2>Formulaire d'inscription</h2>
  <p>Tous les champs doivent être remplis.</p>
  <div class="container mt-3">
    <form @submit.prevent="addUtilisateur">
      <div class="mb-3">
        <!--<select id="roleSelected" required="required" class="form-control">
          <option selected disabled hidden>Choisissez un rôle</option>
          <option
            v-for="country in data.allRoles"
            :key="role.id"
            :value=""
          >
          {{ role.name }}
          </option>
        </select>-->
        <select id="roleSelected" required="required" class="form-control" v-model="data.utilisateur.idRole">
          <option selected disabled hidden>Choisissez un rôle</option>
          <option>Maître de stage</option>
          <option>Etudiant</option>
          <option>Tuteur de stage</option>
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
        <input type="tel" class="form-control" required="required" v-model="data.utilisateur.numTel" />
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
        <input type="motDePasse" required="required" class="form-control" v-model="data.utilisateur.motDePasse" />
      </div>
      <button type="submit" class="btn btn-primary">S'inscrire</button>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";


const emptyutilisateur = {
  idRole: "",
  id: "",
  firsName: "",
  nom: "",
  numTel: "",
  email: "",
  identifiant: "",
  motDePasse: "",
};

const data = reactive({
  utilisateur: {...emptyUtilisateur},
});

defineExpose({
  data,
})


const emit = defineEmits(['useurAdded',])

function addUtilisateur() {
  const options = {
    method: "POST",
    mode: "cors",
    body: JSON.stringify(data.utilisateur),
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("api/utilisateurs", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.utilisateur = {...emptyUtilisateur}; // On réinitialise le formulaire
      emit('utilisateurAdded', json); // On notifie le parent que le pays a été ajouté
    })
    .catch((error) => alert(error));
}
</script>