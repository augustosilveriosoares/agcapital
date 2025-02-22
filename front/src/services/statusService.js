import axios from 'axios';

const API_URL = 'http://localhost:8080/api/status';

export default {
    getStatus() {
        return axios.get(API_URL)
            .then(response => response.data) // Pode extrair a resposta diretamente aqui
            .catch(error => {
                console.error('Error fetching status:', error);
                throw error;
            });
    }
};
