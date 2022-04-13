<script setup>
import { reactive, onMounted } from "vue";
import ListAllStage from "@/components/ListAllStage.vue";

</script>

<template>
<h1> Liste de tous les stages </h1>
<ListAllStage/>
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
