import axios from 'axios';

class ConnexionsDataService {
  getAll() {
    return axios.get("http://localhost:8080/paymybuddy/connexions");
  }

  get() {
    return axios.get(`http://localhost:8080/paymybuddy/connexions/1`);
  }
  create(data) {
    const response = axios.post(
      "http://localhost:8080/paymybuddy/connexions/nouvelleConnexion",
      data
    );

    return (this.connexion = response.data);
  }
}

export default new ConnexionsDataService();
