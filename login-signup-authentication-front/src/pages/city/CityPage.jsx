import { useEffect, useState } from 'react';
import {
    createCity,
    deleteCity,
    getAllCities,
    updateCity
} from '../../services/requests/cityService';
import CityForm from './CityForm';
import './cityPage.css';

const CityPage = () => {
  const [cities, setCities] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [selectedCity, setSelectedCity] = useState(null);

  const loadCities = async () => {
    const data = await getAllCities();
    setCities(data);
  };

  useEffect(() => {
    loadCities();
  }, []);

  const handleCreate = async (city) => {
    await createCity(city);
    setShowForm(false);
    loadCities();
  };

  const handleUpdate = async (city) => {
    await updateCity(city);
    setSelectedCity(null);
    setShowForm(false);
    loadCities();
  };

  const handleDelete = async (id) => {
    await deleteCity(id);
    loadCities();
  };

  const handleEditClick = (city) => {
    setSelectedCity(city);
    setShowForm(true);
  };

  const handleAddClick = () => {
    setSelectedCity(null);
    setShowForm(true);
  };

  return (
    <div className="city-container">
      <div className="city-header">
        <h2>City Management</h2>
        <button className="city-button" onClick={handleAddClick}>
          Add City
        </button>
      </div>

      {showForm && (
        <CityForm
          onSubmit={selectedCity ? handleUpdate : handleCreate}
          initialData={selectedCity}
        />
      )}

      <table className="city-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>City Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {cities.map((city) => (
            <tr key={city.id}>
              <td>{city.id}</td>
              <td>{city.name}</td>
              <td className="city-actions">
                <button className="edit" onClick={() => handleEditClick(city)}>
                  Edit
                </button>
                <button className="delete" onClick={() => handleDelete(city.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CityPage;