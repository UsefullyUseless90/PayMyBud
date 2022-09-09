<script>
  import TransactionsDataService from '../../services/TransactionsDataServices'
  
  export default {
    name: 'transactions-item',
    data() {
        return {
            transactions: []
        }
    },
    methods: {
        retrieveTransactions() {
            TransactionsDataService.getAll()
                .then(response => {
                    this.transactions = response.data
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.retrieveTransactions()
    }
  }
  </script>