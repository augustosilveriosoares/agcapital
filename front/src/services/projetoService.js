import axios from 'axios';

const API_URL = 'http://localhost:8080/api/projetos';

export default {
  getProjetos(page = 0, size = 10) {
    return axios.get(API_URL, {
      params: {
        page: page,
        size: size
      }
    });
  },

  createProjeto(projeto) {
    return axios.post(API_URL, projeto)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  updateProjeto(projeto) {
    return axios.put(`${API_URL}/${projeto.id}`, projeto)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  deleteProjeto(id) {
    return axios.delete(`${API_URL}/${id}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  }
};
