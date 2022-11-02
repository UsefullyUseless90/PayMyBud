
import axios from "axios";
import { createStore } from "vuex";
import { auth } from "../auth.module";

const store = createStore({
  modules: {
    auth
  },

mutations: {
  setToken: (state, model) => {
    state.token = model.token;
    state.expiration = new Date(model.expiration)
  }
},
});
export default store;