import { createRouter, createWebHistory } from "vue-router";

import Home from "@/views/Home.vue";

import Signup from "@/views/authentification/Signup.vue";
import Signin from "@/views/authentification/Signin.vue";
import Signout from "@/views/authentification/Signout.vue";
import Profile from "@/views/authentification/Profile.vue";

import InternshipForm from "@/views/forms/InternshipForm.vue";
import CompanyForm from "@/views/forms/CompanyForm.vue";

import Internship from "@/views/internships/Internship.vue";
import AllInternships from "@/views/internships/AllInternships.vue";
import FreeInternships from "@/views/internships/FreeInternships.vue";
import InProgressInternships from "@/views/internships/InProgressInternships.vue";
import PendingInternships from "@/views/internships/PendingInternships.vue";
import InternshipsByCompany from "@/views/internships/InternshipsByCompany.vue";
import InternshipsByStudent from "@/views/internships/InternshipsByStudent.vue";
import InternshipsByTutor from "@/views/internships/InternshipsByTutor.vue";

import Companies from "@/views/lists/Companies.vue";
import Students from "@/views/lists/Students.vue";
import Tutors from "@/views/lists/Tutors.vue";

const routes = [
    // home
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    // authentification
    {
        path: "/signup",
        name: "Signup",
        component: Signup,
    },
    {
        path: "/signin",
        name: "Signin",
        component: Signin,
    },
    {
        path: "/signout",
        name: "Signout",
        component: Signout,
    },
    {
        path: "/profile",
        name: "Profile",
        component: Profile,
    },
    // forms
    {
        path: "/internshipForm",
        name: "InternshipForm",
        component: InternshipForm,
    },
    {
        path: "/companyForm",
        name: "CompanyForm",
        component: CompanyForm,
    },
    // internships
    {
        path: "/internship/:id",
        name: "Internship",
        component: Internship,
        props: true,
    },
    {
        path: "/allInternships",
        name: "AllInternships",
        component: AllInternships,
    },
    {
        path: "/freeInternships",
        name: "FreeInternships",
        component: FreeInternships,
    },
    {
        path: "/inProgressInternships",
        name: "InProgressInternships",
        component: InProgressInternships,
    },
    {
        path: "/pendingInternships",
        name: "PendingInternships",
        component: PendingInternships,
    },
    {
        path: "/internshipsByCompany/:id",
        name: "InternshipsByCompany",
        component: InternshipsByCompany,
        props: true,
    },
    {
        path: "/internshipsByStudent/:id",
        name: "InternshipsByStudent",
        component: InternshipsByStudent,
        props: true,
    },
    {
        path: "/internshipsByTutor/:id",
        name: "InternshipsByTutor",
        component: InternshipsByTutor,
        props: true,
    },
    // lists
    {
        path: "/companies",
        name: "Companies",
        component: Companies,
    },
    {
        path: "/students",
        name: "Students",
        component: Students,
    },
    {
        path: "/tutors",
        name: "Tutors",
        component: Tutors,
    },
    // about
    {
        path: "/about",
        name: "About",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ "../views/About.vue"),
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/signin', '/signup', '/signout', '/'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');
    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !loggedIn) {
        next('/signin');
    } else {
        next();
    }
});

export default router;