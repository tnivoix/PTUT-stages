<script>
export default {
    name: 'Logout',
    created() {
        this.$store.dispatch('auth/logout');
        setTimeout(() => {
            this.$router.push('/');
            setTimeout(() => {
                this.$router.go();
            }, 50);
        }, 400);
    }
};
</script>