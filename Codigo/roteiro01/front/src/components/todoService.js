import axios from 'axios';

// Endereço base da API
const API_URL = 'http://localhost:8080/api/task';

export const getTodos = () => {
    return axios.get(API_URL);
};

export const createTodo = (todo) => {
    return axios.post(API_URL, todo);
};

export const updateTodo = (id, todo) => {
    return axios.put(`${API_URL}/${id}`, todo);
};

export const deleteTodo = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};

export const completeTodo = (id) => {
    return axios.put(`${API_URL}/${id}/complete`);
};
