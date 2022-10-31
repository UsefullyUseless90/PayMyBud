import axios from 'axios';

const API_URL = 'http://localhost:8080/paymybuddy/login';

class AuthService {
  async login(utilisateurDAO) {
    return axios
      .post(API_URL, {
        nom_prenom: utilisateurDAO.nomPrenom,
        mot_De_Passe: utilisateurDAO.motDePasse
      })
      .then(response => {
    if (response.data.accessToken) {
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  })
}
  logout() {
    localStorage.removeItem('user');
  }

  register(utilisateurDAO) {
    return axios.post(API_URL + '/inscription', {
      Nom: utilisateurDAO.nom,
      Prenom: utilisateurDAO.prenom,
      Adresse_Email: utilisateurDAO.adresseEmail,
      Date_De_Naissance: utilisateurDAO.dateDeNaissance,
      mot_De_Passe: utilisateurDAO.motDePasse
    });
  }
}

export default new AuthService();