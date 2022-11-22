<script>
import ConnexionDataServices from "../../services/ConnexionDataServices";

export default {
  name: "connexions-item",
  data() {
    return {
      connexions: [],
    };
  },
  methods: {
    retrieveConnexions() {
      ConnexionDataServices.getAll()
        .then((response) => {
          this.connexions = response.data;
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
  mounted() {
    this.retrieveConnexions();
  },
};
</script>
