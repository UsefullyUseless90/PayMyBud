# PayMyBud
# PayMyBud
Projet n°6 : Pay My Buddy
Le projet concerne la conception d’une application facilitant le transfert de fonds entre particulier. <br/>

Le repository contient les éléments suivant : <br/>
Le script de création de base de données <br/>
Le modèle de concetion physique de données,<br/>
Le diagramme UML,<br/>
La partie FrontEnd (Vue js 2.6.14),<br/>
La partie BackEnd (Java 17),<br/>
Le rapport de couverture de test JACOCO.<br/>
<br/>
<br/>
Mise en place de la BDD PhpMyAdmin :<br/>
Installer PhpMyAdmin : https://www.phpmyadmin.net/<br/>
Lancer PhpMyAdmin en utilisant la version 4.9.7 :<br/>
 <br/>
Le username est root<br/>
Le mot de passe est root <br/>
<br/>
Une fois dans l'espace d'accueil vous pourrez appercevoir l’onglet import, dans celui ci: <br/>
 <br/>
Inserer le script joint dans le repository.<br/>
Le script créera :<br/>
Les 4 tables suivantes :<br/>
	commissions_pmb<br/>
	connexions<br/>
	transactions<br/>
	utilisateurs<br/>
L’ensemble des utilisateurs fictifs créer pour les essais.<br/>
<br/>
Gestion du BackEnd 
L’installation de Maven et SDK17 sont nécessaires<br/>
/!\ Important /!\<br/>
Afin de ne pas avoir de conflit d'adressage IP lors du lancement,<br/> 
veillez à démarrer en premier le backend qui se mettra automatiquement sur le port 8080.<br/>
<br/>
Gestion FrontEnd<br/>
<br/>
Installer npm<br/> 
Une fois fait, lancer la commande npm install dans le terminal après l’avoir dirigé dans la partie frontend.<br/>
Pour lancer le front la commande suivante est nécessaire : npm run serve<br/>
Deux URL s’afficheront vous pourez alors utiliser l’interface prototype du front.<br/>
<br/>
Ci-joint un extrait des dependances et leur version issue du package.json<br/>
<br/>
"dependencies": {<br/>
    "@fortawesome/fontawesome-svg-core": "^6.2.0",<br/>
    "@fortawesome/free-solid-svg-icons": "^6.2.0",<br/>
    "@fortawesome/vue-fontawesome": "^2.0.9",<br/>
    "axios": "^0.19.0",<br/>
    "bootstrap": "^4.6.2",<br/>
    "core-js": "^3.8.3",<br/>
    "jquery": "^3.6.1",<br/>
    "popper.js": "^1.16.1",<br/>
    "vee-validate": "^2.2.15",<br/>
    "vue": "^2.6.14",<br/>
    "vue-router": "^3.0.3",<br/>
    "vuex": "^3.0.1",<br/>
    "webpack": "^5.75.0"<br/>
    <br/>
     Diagramme UML <br/>
    <br/>
![UML PMB](https://user-images.githubusercontent.com/84840316/203286466-825c2dd8-eaa7-41ff-bbf9-fd172434689b.jpg)
    <br/>
    MPD<br/>
    <br/>
![MPD](https://user-images.githubusercontent.com/84840316/203287723-c4dfe729-f926-4f31-af94-4ad26ef1aee8.png)
