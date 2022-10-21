import axios from "axios";

class TransactionsDataService {
  getAll() {
    return axios.get("http://localhost:8080/paymybuddy/transaction");
  }

  // modification necessaire afin d'éviter les conflit entre emetteur et receveur lors des resultats requête.
  getDestinataire() {
    return axios.get(`http://localhost:8080/paymybuddy/transactions/1`);
  }

  create(data) {
    const response = axios.post(
      "http://localhost:8080/paymybuddy/transactions/nouvelleTransaction",
      data
    );

    return (this.transaction = response.data);
  }

  //update(id, data) {
  //  return http.put(`http://localhost:8080/paymybuddy/transaction/`, data)
  //}

  //delete(id) {
  //    return http.delete(`/customers/${id}`)
  //}
}

export default new TransactionsDataService();
