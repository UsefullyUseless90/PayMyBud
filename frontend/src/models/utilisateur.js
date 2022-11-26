export default class utilisateurDTO {
  constructor(idUtilisateur,nomPrenom,adresseEmail,motDePasse, actif, role) {
    this.idUtilisateur = idUtilisateur;
    this.nomPrenom = nomPrenom;
    this.adresseEmail = adresseEmail;
    this.motDePasse = motDePasse;
    this.actif = actif;
    this.role = role;
  }
}