import axios from 'axios';
import Cliente from "@/models/Cliente.js";

const API_URL = 'http://localhost:8080/api/clientes';

export default {
  getClientes(page = 0, size = 5) {
    return axios.get(API_URL, {
      params: {
        page: page,
        size: size
      }
    });
  },

  getClienteById(id) {
    return axios.get(`${API_URL}/${id}`)
        .then(response => response.data)
        .catch(error => {
          throw error;
        });
  },

  createCliente(cliente) {
        return axios.post(API_URL, cliente, {
     }).then(response => response.data) // Retornar os dados do cliente criado
      .catch(error => {
          // Lançar o erro para ser tratado no método chamador
          throw error;
        });
  },



  updateCliente(cliente) {
    return axios.put(`${API_URL}/${cliente.id}`, cliente)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  deleteCliente(id) {
    return axios.delete(`${API_URL}/${id}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  }
};
