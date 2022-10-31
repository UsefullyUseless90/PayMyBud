import axios from "axios";
import authHeader from "./auth-header";

const API_URL = '';

class UtilisateurDataServices {
  getAll() {
    return axios.get(API_URL + "utilisateurs");
  }

  getPartieUtilisateur() {
    return axios.get(API_URL + '/user', { headers: authHeader() });
  }

  get(id) {
    return axios.get(`/utilisateur/${id}`);
  }

  create(data) {
    const response = axios.post(
      "http://localhost:8080/paymybuddy/inscription", data);

      return (this.utilisateur = response.data);
  }
  login(data) {
    const response = axios.post(
      "http://localhost:8080/paymybuddy/login", data);
     return (this.utilisateurDAO = response.data) 
  }
  logout(){
    return axios.get(
      "http://localhost:8080/paymybuddy/logout"
    )
  }

  update(id, data) {
    return axios.put(`/utilisateur/${id}`, data);
  }

  delete(id) {
    return axios.delete(`/utilisateur/${id}`);
  }
}

export default new UtilisateurDataServices();
