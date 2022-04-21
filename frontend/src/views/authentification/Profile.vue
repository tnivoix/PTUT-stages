<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        Page utilisateur : <strong>{{ currentUser.username }}</strong>
      </h3>
    </header>
    <p>
      <strong>Token:</strong>
      {{ currentUser.accessToken.substring(0, 20) }} ... {{ currentUser.accessToken.substr(currentUser.accessToken.length -
          20)
      }}
    </p>
    <p>
      <strong>Id:</strong>
      {{ currentUser.id }}
    </p>
    <p>
      <strong>Identifiant:</strong>
      {{ currentUser.username }}
    </p>
    <p>
      <strong>Email:</strong>
      {{ currentUser.email }}
    </p>
    <p>
      <strong>Nom:</strong>
      {{ currentUser.name }}
    </p>
    <p>
      <strong>Prénom:</strong>
      {{ currentUser.firstName }}
    </p>
    <p>
      <strong>NumTel:</strong>
      {{ currentUser.numTel }}
    </p>
    <p>
      <strong>Role:</strong>
      {{ currentUser.roles[0] }}
    </p>
  </div>
</template>
<script>
export default {
  name: 'Profile',
  computed: {
    currentUser() {
      console.log(this.$store.state.auth.user);
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>