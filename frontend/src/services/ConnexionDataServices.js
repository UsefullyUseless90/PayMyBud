import axios from 'axios';
import authHeader from './auth-header';

class ConnexionsDataService {
  // Connexion Utilisateurs
  getAll() {
    return axios.get("http://localhost:8080/paymybuddy/user/connexions");
  }

  getHistoriqueConnexion(idUtilisateur) {
    return axios.get(`http://localhost:8080/paymybuddy/user/connexions/` + idUtilisateur, { headers : authHeader() });
  }
  create(idUtilisateur, data) {
    const response = axios.post( "http://localhost:8080/paymybuddy/user/connexions/nouvelleConnexion/"+ idUtilisateur, data, {headers: authHeader()});
    return (this.connexion = response.data);
  }
  // Connexion Banque
  createLinkBank(idUtilisateur, data) {
    return axios.post( "http://localhost:8080/paymybuddy/transfert/nouvelleConnexion/"+ idUtilisateur, data, {headers: authHeader()});
  
  }
  getHistoriqueCompte(idUtilisateur) {
    return axios.get(`http://localhost:8080/paymybuddy/transfert/index/` + idUtilisateur, { headers : authHeader() });
  }
}

export default new ConnexionsDataService();
