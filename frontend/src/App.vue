<script setup>
  import SignUpForm from "@/components/SignUpForm.vue";
  import SignUpCompany from "@/components/SignUpCompany";
</script>


<template>
  <div>
    <div id="nav">
      <SignUpForm/>
      <SignUpCompany/>
    </div>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
