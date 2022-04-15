import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import InternshipFormPage from "@/views/InternshipFormPage.vue";
import Inscription from "@/views/Inscription.vue";
import InscriptionEntreprise from "@/views/InscriptionEntreprise.vue";
import InterfaceConnexion from "@/views/InterfaceConnexion.vue";

const routes = [{
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/internshipFormPage",
        name: "InternshipFormPage",
        component: InternshipFormPage,
    },

    {
        path: "/Inscription",
        name: "Inscription",
        component: Inscription,
    },
    {
        path: "/InscriptionEntreprise",
        name: "InscriptionEntreprise",
        component: InscriptionEntreprise,
    },
    {
        path: "/Connexion",
        name: "Connexion",
        component: InterfaceConnexion,
    },
    {
        path: "/about",
        name: "About",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ "../views/About.vue"),
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;