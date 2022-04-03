<script setup>
import { reactive, onMounted } from "vue";
import ListEntreprise from "@/components/ListEntreprise.vue";





</script>

<template>
 
 <h1> Stage </h1>
<!-- <ListEntreprise/> -->
<div class = "Introduction">
    <div> Organisation / Entreprise : {{ stage.entreprise.name }}</div>
    <div> Adresse Entreprise : {{ stage.adresse }}  {{ stage.ville }} ,  {{ stage.CodePostal }} , {{ stage.pays }}</div>
    <div> Maître de stage : {{ stage.maitreDeStage }} , {{ stage.fonction }} </div>
    <div> Date de debut :{{ stage.dateDebut }} </div>
    <div> Durée : {{ stage.duree }} </div>
    <div> Niveau d'etude : {{ stage.anneeEtude }}</div>
    
</div>
<div class = "Partie1"> Description du stage : 
  <div>  Contexte : {{ stage.contexte }} </div>
  <div>  Sujet : {{ stage.sujet }} </div>
  <div>  Service : {{ stage.service }} </div>
</div>
<!-- Contexte Sujet et Service  -->
<div class = "Partie2"> Activités : {{ stage.activites }}
    <div> Compétences : {{ stage.competences }} </div>
</div>
<!-- Activite et Compétence  -->


<div class = "Partie3"> Commentaires suplémentaires : 
<!-- Remuneration, horaire, commentaires  -->
{{ stage.remuneration }}
{{ stage.horaire }}
{{ stage.commentaires }}
</div>

</template>

<style>


div {
    margin-top: 5px;
    margin-bottom: 5px;
    color : #424949;
    
}


h1 {
    margin-top: 5px;
    margin-bottom: 5px;
    text-align: center;
    color : black;
}

div.Introduction, div.Partie1, div.Partie2, div.Partie3 {
    border-radius: 50px / 50px;
    /*border: 2px solid #E5E7E9 ;*/
    padding-left: 50px;
    padding-right: 5px;
    background-color: #F8F9F9 ;
}
 
  

</style>
