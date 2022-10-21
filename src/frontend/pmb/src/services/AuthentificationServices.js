import axios from 'axios';

const API_URL = 'http://localhost:8080/paymybuddy/login';

class AuthService {
  async login(utilisateurDAO) {
    const response = await axios
      .post(API_URL + 'signin', {
        nom_prenom: utilisateurDAO.nomPrenom,
        mot_De_Passe: utilisateurDAO.motDePasse
      });
    if (response.data.accessToken) {
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(utilisateurDAO) {
    return axios.post(API_URL + 'signup', {
      Nom: utilisateurDAO.nom,
      Prenom: utilisateurDAO.prenom,
      Adresse_Email: utilisateurDAO.adresseEmail,
      Date_De_Naissance: utilisateurDAO.dateDeNaissance,
      mot_De_Passe: utilisateurDAO.motDePasse
    });
  }
}

export default new AuthService();