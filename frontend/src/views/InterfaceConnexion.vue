<template>
    <div id="interfaceConnexion">
        <h2>Connexion Utilisateur</h2>
        <p>Tous les champs doivent être remplis.</p>
        <div class="container mt-3">
            <form @submit.prevent="handleLogin">
                <div class="mb-3">
                    <label for="identifiant" class="form-label">Identifiant :</label>
                    <input class="form-control" required="required" name="identifiant" v-model="user.username" />
                </div>
                <div class="mb-3">
                    <label for="motDePasse" class="form-label">Mot de passe :</label>
                    <input type="password" class="form-control" name="motDePasse" required="required"
                        v-model="user.password" />
                </div>
                <button type="submit" class="btn btn-primary">Se connecter</button>
            </form>
            <div v-if="message" class="alert alert-danger" role="alert">{{ message }}</div>
        </div>
    </div>
</template>

<script>
import User from '../models/user';
export default {
    name: 'Login',
    data() {
        return {
            user: new User('', ''),
            loading: false,
            message: ''
        };
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        }
    },
    created() {
        if (this.loggedIn) {
            this.$router.push('/profile');
        }
    },
    methods: {
        handleLogin() {
            this.loading = true;
            if (this.user.username && this.user.password) {
                this.$store.dispatch('auth/login', this.user).then(
                    () => {
                        this.$router.push('/profile');
                    },
                    error => {
                        this.loading = false;
                        this.message =
                            (error.response && error.response.data) ||
                            error.message ||
                            error.toString();
                    }
                );
            }
        }
    }
};
</script>