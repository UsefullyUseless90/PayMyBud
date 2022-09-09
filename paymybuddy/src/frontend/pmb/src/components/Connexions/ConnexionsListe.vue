<script>
  import ConnexionDataServices from '../../services/ConnexionDataServices'
  
  export default {
    name: 'connexiions-item',
    data() {
        return {
            connexions: []
        }
    },
    methods: {
        retrieveConnexions() {
            ConnexionDataServices.getAll()
                .then(response => {
                    this.connexions = response.data
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.retrieveConnexions()
    }
  }
  </script>