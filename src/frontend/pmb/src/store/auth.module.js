const state = {
  isAuthenticated: localStorage.getItem('auth'),
  user:null,
  error: null,
  loading:null,
  };

const getters = {
  getIsAuthenticated(state) {
    return state.isAuthenticated;
  },
  getCurrentUser(state) {
    return state.currentUser;
  },
};

const mutations = {
  setUser(state, payload) {
    state.user = payload;
  },
  setAuth(payload) {
    localStorage.setItem('auth', payload);
  },
  clearAuth() {
    localStorage.removeItem('auth');
  },
  setError(state, payload) {
    state.error = payload;
  },
  setLoading(state, payload) {
    state.loading = payload;
  },
};


const actions = {
  userSignIn({ commit }, payload) {
    const data = {
      username: payload.username,
      password: payload.password,
    };
   
    commit('setLoading', true);
    axios.post('http://localhost:8080/login/signin', data)
      .then(() => {
        commit('setAuth', true);
        commit('setLoading', false);
        commit('setError', null);
        EventBus.$emit('authenticated', 'User authenticated');
        router.push('/home');
      })
      .catch((error) => {
        commit('setError', error.message);
        commit('setLoading', false);
      });
  },
  userSignOut({ commit }) {
    commit('clearAuth');
    EventBus.$emit('authenticated', 'User not authenticated');
    router.push('/signIn');
  },
};


export default {
  state,
  getters,
  mutations,
  actions
};
