import axios from 'axios'

class TransactionsDataService {
  getAll() {
      return axios.get('http://localhost:8080/paymybuddy/transaction')
  }

  getEmetteur() {
      return axios.get(`http://localhost:8080/paymybuddy/transaction/emetteur`)
  }
  
  // modification necessaire afin d'éviter les conflit entre emetteur et receveur lors des resultats requête. 
  getDestinataire() { 
    return axios.get(`http://localhost:8080/paymybuddy/transaction/receveur`)
  }

  create(data) {
      return axios.post('http://localhost:8080/paymybuddy/transaction/nouvelleTransaction', data)
  }

  //update(id, data) {
    //  return http.put(`http://localhost:8080/paymybuddy/transaction/`, data)
  //}

  //delete(id) {
  //    return http.delete(`/customers/${id}`)
  //}
}

export default new TransactionsDataService()