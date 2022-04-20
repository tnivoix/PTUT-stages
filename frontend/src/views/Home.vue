<template>
<div id="presentation">
    <div id="image"><img id="work" alt="travailleurs" src="../assets/work.png" /></div>
  <div id="titres">
    <h3 id="titre">Gestion des stages</h3>
    <h2 id="sous-titre">Projet réalisé dans le cadre du projet tutoré <br/> FIE3 2021-2022</h2>
  </div>
  <div class="home">
    <div id="logo"><img id="logoISIS" alt="logo ISIS" src="../assets/logoISIS.png" /></div>
  </div>
  <p id="membres">Membre du groupe : BOURDARIAS Cléa | CHAUBET Paul | KESKIN Defne | LASSERVARIE Gabin | NIVOIX Thomas</p>
</div>
</template>

<script setup>
</script>

<style>

div#presentation{
  font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  position: absolute;
  height: 70%;
  width: 100%;
  left: 0px;
  bottom:0px;
  display:block;
  z-index: -1;
}
img#logoISIS{
  position: relative;
  height: 120px;
  width: 150px;
}

p#membres{
  position: absolute;
  bottom:0px;
  width: 100%;
  align-content: center;
}

div#logo {
  position:absolute;
  padding: 15px;;
  bottom:100px;
  background-color:  rgb(132, 159, 210);
  left:10%;
}

h3#titre{
  position:relative;
  font-size:50px;
  text-align: left;
}

h2#sous-titre{
  position:relative;
  left:20px;
  font-size:25px;
  text-align: left;
}

div#titres{
  position:absolute;
  left:10%;
  bottom:300px;
}

img#work{
  position:relative;
  width: 700px;
}

div#image{
  position:relative;
  bottom:20px;
  left:45%;
  border:solid;
  width: 704px;
}

</style>
