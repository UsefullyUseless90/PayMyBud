<template>
  <h1>Historique des transactions</h1>
  <div id="app">
    <h1>Bonjour {{utilisateur}} !</h1>
  </div>
  <div class="gestion_entete">
    <h2>Envoyer de l'argent <button class="button1"><a href="contact_page.html">Ajouter contact</a></button></h2>
  </div>
  <div class="carre" style="background-color:lightgrey;">
    <div class="boutons">
      <select name="contacts" id="contact-select">
        <option value="">--Choisissez un contact--</option>
        <option value="contact1">Contact1</option>
        <option value="contact2">Contact2</option>
        <option value="contact3">Contact3</option>
        <option value="contact4">Contact4</option>
        <option value="contact5">Contact5</option>
        <option value="contact6">Contact6</option>
      </select>
      <input type="number" id="tentacles" name="tentacles" min="01" max="100000"/>
      <button class="button2" type="button" id="payer" @click= "fetchAPIData" >Payer</button>
    </div>
    </div>
    <p>Mes transactions</p>
  <table class="center">
    <thead>
      <tr>
        <th colspan="3"></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Contact</td>
        <td>Description</td>
        <td>Montant</td>
      </tr>
    </tbody>
  </table>
  <div>
      <hr>
      <p>
          <i>{{result}}</i>
      </p>
      <hr>
  </div>
  <table class="pages">
    <thead>
      <tr>
        <th colspan="5"></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a href="contenu/page2.html">1</a></td>
        <td><a href="contenu/page2.html">2</a></td>
        <td><a href="contenu/page2.html">3</a></td>
        <td><a href="contenu/page2.html">4</a></td>
        <td><a href="contenu/page2.html">5</a></td>
      </tr>
    </tbody>
  </table>
</template>

<script>
 import TransactionsDataServices from '@/services/TransactionsDataServices';

export default{
 name: "recuperation-transactions",
 data(){
   return{
  
   };
 },
 created(){
   TransactionsDataServices.getAll
 }
};
</script>  

<style>
select{
  height: 50px;
  width: 350px;
  margin-left: 120px;
}
label {
  display: block;
  font: 1rem 'Fira Sans', sans-serif;

}
h2{
  margin-left: 500px;
  font-weight: bold;
  font-size: xx-large;
  margin-top: auto;
  margin-bottom: auto;
}
.boutons{
  margin-left: 250px;
  margin-right: auto;
  margin-top: 50px;
}

input,
label {
  height: 45px;
  width: 150px;
  border-radius: 5px / 5px;
  margin-left: 20px;
}
.button1{
  margin-left: 1000px;
  background-color: blue;
  color: #fff;
  height: 50px;
  width: 250px;
  border:1px green;
  border-radius: 20px / 20px;
  font-weight: bold;
  font-size: x-large;
}
.button2{
  background-color: green;
  color: #fff;
  height: 50px;
  width: 250px;
  border:1px green;
  border-radius: 20px / 20px;
  margin-left: 25px;
  font-weight: bold;
  font-size: x-large;
}
.rectangle{
  width: 2600px;
  height: 50px;
  font-size: x-large;
}
.gestion_entete{
  display: inline-block;

}
.carre {
  display: flex;
  width: 1550px;
  height: 150px;
  margin-left: 500px;
  
}
.liens{
  margin-top: auto;
  margin-left: auto;
}
p{
margin-left: 500px;
margin-right: auto;
display: flex;
justify-content: flex-start;
font-weight: bold;
font-size: xx-large;
}
.center {
  margin-left: 497px;
  margin-right: auto;
  border: 2px solid black;
  background-color: green;
}
.liste
{
  text-align: center;
  display: flex;
  justify-content: space-evenly;
}
table,
td {
  text-align: center;
  font-weight: bold;
  font-size: xx-large;
  color: #fff;
  height: 150px;
  width: 1550px;

}

thead,
tfoot {
    text-align: center;
}
.pages{
display: flex;
justify-content: center;
font-size: xxx-large;
align-content: space-evenly;
background-color: white;
margin-left: 497px;
border: 2px solid black;
margin-top: 450px;
}

</style>


