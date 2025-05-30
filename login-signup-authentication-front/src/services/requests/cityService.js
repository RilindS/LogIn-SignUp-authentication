import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/city';

export const getAllCities = async () => {
  const response = await axios.get(`${BASE_URL}/all`);
  return response.data.data; 
};

export const createCity = async (city) => {
  const response = await axios.post(`${BASE_URL}/create`, city);
  return response.data;
};

export const updateCity = async (city) => {
  const response = await axios.put(`${BASE_URL}/update`, city);
  return response.data;
};

export const deleteCity = async (id) => {
  const response = await axios.delete(`${BASE_URL}/delete/${id}`);
  return response.data;
};
