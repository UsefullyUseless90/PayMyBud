import axios from "axios";

class UtilisateurDataServices {
  getAll() {
    return axios.get("http://localhost:8080/paymybuddy/utilisateurs");
  }

  get(id) {
    return axios.get(`/utilisateur/${id}`);
  }

  create(data) {
    return axios.post("/inscription", data);
  }

  update(id, data) {
    return axios.put(`/utilisateur/${id}`, data);
  }

  delete(id) {
    return axios.delete(`/utilisateur/${id}`);
  }
}

export default new UtilisateurDataServices();
