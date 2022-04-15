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
    <form @submit.prevent="handleRegister">
      <div class="mb-3">
        <select id="roleSelected" required class="form-control" v-model="user.roles[0]">
          <option selected disabled hidden value="">Choisissez un rôle</option>
          <option v-for="role in allRoles" :key="role.id" :value="role._links.self.href">
            {{ role.nom }}
          </option>
        </select>
      </div>
      <div class="mb-3">
        <label for="prenom" class="form-label">Prénom :</label>
        <input class="form-control" required="required" v-model="user.firstName" />
      </div>
      <div class="mb-3">
        <label for="nom" class="form-label">Nom de famille :</label>
        <input class="form-control" required="required" v-model="user.name" />
      </div>
      <div class="mb-3">
        <label for="numTel" class="form-label">Numéro de téléphone :</label>
        <input type="tel" pattern="[00-99]{2}[00-99]{2}[00-99]{2}[00-99]{2}[0-99]{2}" class="form-control"
          required="required" v-model="user.numTel" />
        <small>Format: 0123456789</small>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">@email :</label>
        <input class="form-control" required="required" type="email" v-model="user.email" />
      </div>
      <div class="mb-3 mt-3">
        <label for="identifiant" class="form-label">Identifiant :</label>
        <input class="form-control" required="required" v-model="user.username" />
      </div>
      <div class="mb-3">
        <label for="motDePasse" class="form-label">Mot de passe :</label>
        <input type="password" required="required" class="form-control" v-model="user.password" />
      </div>
      <button type="submit" class="btn btn-primary">S'inscrire</button>
    </form>
    <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">{{ message }}</div>
  </div>
</template>

<script>
import User from '../models/user';
export default {
  name: 'Register',
  data() {
    return {
      user: new User('', '', '', '', '', '', []),
      submitted: false,
      successful: false,
      message: '',
      allRoles: []
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    } else {
      fetch("api/roles").then((json) => {
        allRoles = json._embedded.roles;
      });
    }
  },
  methods: {
    handleRegister() {
      this.message = '';
      this.submitted = true;
      this.$store.dispatch('auth/register', this.user).then(
        data => {
          this.message = data.message;
          this.successful = true;
        },
        error => {
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          this.successful = false;
        }
      );
    }
  }
};
</script>