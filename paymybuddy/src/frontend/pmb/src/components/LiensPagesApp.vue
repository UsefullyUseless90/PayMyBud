<template>
    <header class="head">
  <div class="liens">
    <a href="">Accueil</a>
    <a href="/transactions">Transactions</a>
    <a href="/editionProfil">Profil</a>
    <a href="/contact">Contact</a>
    <a href="/deconnexion">Déconnexion</a>
  </div>
</header>
<div class="rectangle" style="background-color:lightgrey;">
  <a href="Accueil_pmb.html">Accueil /</a>
</div>
</template>
  
  <script>

    export default {
        
        
    }
    </script>  

    <style lang="scss">
      
      .head{
        font-size: 40px;
        height: 200px;
        color: white;
        margin-top: auto;
        display: flex;
        justify-content: space-evenly;
      }

      .liens{
        text-align: right;
        font-size: x-large;
        display: flex;
        margin-left: 1200px;
        justify-content: space-evenly;
        
      }

      .rectangle{
        width: 2600px;
        height: 50px;
        font-size: x-large;

      }
    </style>