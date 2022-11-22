
import axios from "axios";
import authHeader from "./auth-header";

class TransactionsDataService {
  getAll() {
    return axios.get("http://localhost:8080/paymybuddy/transaction");
  }

  getHistoriqueTransactions(idUtilisateur) {
    return axios.get(`http://localhost:8080/paymybuddy/user/transactions/` + idUtilisateur, { headers: authHeader() });
  }

  create(idUtilisateur, data) {
    return axios.post("http://localhost:8080/paymybuddy/user/transactions/nouvelleTransaction/" +idUtilisateur , data, {headers: authHeader()});
  }

  //update(id, data) {
  //  return http.put(`http://localhost:8080/paymybuddy/transaction/`, data)
  //}

  //delete(id) {
  //    return http.delete(`/customers/${id}`)
  //}
}

export default new TransactionsDataService();
