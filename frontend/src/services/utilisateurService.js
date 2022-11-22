import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/paymybuddy'; 

class UserService {

  getUtilisateur(idUtilisateur) {
    return axios.get('http://localhost:8080/paymybuddy' + '/utilisateur/' + idUtilisateur, { headers: authHeader() });
  }
  update(idUtilisateur, data) {
    return axios.put(API_URL +'/user/edit/'+ idUtilisateur, data, { headers: authHeader() })
  }
  create(data) {
  const response = axios.post(
    "http://localhost:8080/paymybuddy/inscription", data);

    return (this.utilisateur = response.data);
  }
}

export default new UserService();