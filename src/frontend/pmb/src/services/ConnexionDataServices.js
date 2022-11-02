import axios from 'axios';

class ConnexionsDataService {
  getAll() {
    return axios.get("http://localhost:8080/paymybuddy/user/connexions");
  }

  get() {
    return axios.get(`http://localhost:8080/paymybuddy/user/connexions/1`);
  }
  create(data) {
    const response = axios.post(
      "http://localhost:8080/paymybuddy/user/connexions/nouvelleConnexion",
      data
    );

    return (this.connexion = response.data);
  }
}

export default new ConnexionsDataService();
