// src/pages/UserPage.jsx
import axios from 'axios';
import { useEffect, useState } from 'react';
import './UserPage.css';

const UserPage = () => {
  const [users, setUsers] = useState([]);
  const [editingUser, setEditingUser] = useState(null);
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    imageUrl: '',
    cityId: '',
  });
  const [cities, setCities] = useState([]);

  useEffect(() => {
    fetchUsers();
    fetchCities();
  }, []);

  const fetchUsers = async () => {
    const response = await axios.get('http://localhost:8080/api/users/all');
    setUsers(response.data);
  };

  const fetchCities = async () => {
    const response = await axios.get('http://localhost:8080/api/city/all');
    setCities(response.data.data);
  };


  const handleEditClick = (user) => {
    setEditingUser(user.id);
    setFormData({
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      phoneNumber: user.phoneNumber,
      imageUrl: user.imageUrl,
      cityId: user.city?.id || '',
    });
  };

  const handleUpdate = async () => {
    await axios.put(`http://localhost:8080/api/users/${editingUser}`, formData);
    setEditingUser(null);
    fetchUsers();
  };

  const handleDelete = async (id) => {
    await axios.delete(`http://localhost:8080/api/users/${id}`);
    fetchUsers();
  };

  return (
    <div className="user-page">
      <h2>User List</h2>
      <table className="user-table">
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>City</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.id}>
              <td>{user.firstName}</td>
              <td>{user.lastName}</td>
              <td>{user.email}</td>
              <td>{user.phoneNumber}</td>
              <td>{user.cityName}</td>
              <td>
                <button onClick={() => handleEditClick(user)}>Edit</button>
                <button onClick={() => handleDelete(user.id)} className="delete">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

   {editingUser && (
  <div className="modal-overlay" onClick={() => setEditingUser(null)}>
    <div className="modal-content" onClick={(e) => e.stopPropagation()}>
      <h3>Edit User</h3>
      <input type="text" placeholder="First Name" value={formData.firstName} onChange={(e) => setFormData({ ...formData, firstName: e.target.value })} />
      <input type="text" placeholder="Last Name" value={formData.lastName} onChange={(e) => setFormData({ ...formData, lastName: e.target.value })} />
      <input type="email" placeholder="Email" value={formData.email} onChange={(e) => setFormData({ ...formData, email: e.target.value })} />
      <input type="text" placeholder="Phone Number" value={formData.phoneNumber} onChange={(e) => setFormData({ ...formData, phoneNumber: e.target.value })} />
      <input type="text" placeholder="Image URL" value={formData.imageUrl} onChange={(e) => setFormData({ ...formData, imageUrl: e.target.value })} />
      <select value={formData.cityId} onChange={(e) => setFormData({ ...formData, cityId: e.target.value })}>
        <option value="">Select City</option>
        {cities.map(city => (
          <option key={city.id} value={city.id}>{city.name}</option>
        ))}
      </select>
      <div className="modal-actions">
        <button onClick={handleUpdate}>Save</button>
        <button onClick={() => setEditingUser(null)}>Cancel</button>
      </div>
    </div>
  </div>
)}

    </div>
  );
};

export default UserPage;
