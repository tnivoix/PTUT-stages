<script setup>
import SignUpForm from "@/components/SignUpForm.vue";
import { ref } from "vue";

const list = ref(null);
function refresh() {
  list.value.getUtilisateurs();
}
<template>
  <div>
    <SignUpForm @utilisateurAdded="refresh" />
  </div>
</template>
/*
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
*/