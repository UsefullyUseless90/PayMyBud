
import { createStore } from "vuex";
import { auth } from "../auth.module";

const store = createStore({
  modules: {
    auth
  },
actions: {
  login: async ({ commit }, model) => {
    try {
      commit("setBusy");
      commit("clearError");
      const http = createHttp(false); // unsecured
      const result = await http.post("/api/auth", model);
      if (result.data.success) {
        commit("setToken", result.data);
        router.push("/");
      }
      else {
        commit("setError", "Authentication Failed");
      }
    } catch {
      commit("setError", "Failed to login");
    } finally {
      commit("clearBusy");
    }
  },
},
mutations: {
  setToken: (state, model) => {
    state.token = model.token;
    state.expiration = new Date(model.expiration)
  }
},
});
export default store;