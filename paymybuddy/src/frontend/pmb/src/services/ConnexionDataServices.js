import axios from "axios"


class ConnexionsDataService {
  getAll() {
      return axios.get('http://localhost:8080/paymybuddy/connexions')
  }

  get() {
      return axios.get(`http://localhost:8080/paymybuddy/transaction/id`)
  }
  
}

export default new ConnexionsDataService()