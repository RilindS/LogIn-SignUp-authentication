import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../../services/requests/auth';

const LoginForm = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const [errorMessage, setErrorMessage] = useState(null);
  const navigate = useNavigate()

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await loginUser(formData);
      const token = response.token; 
  
      if (token) {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const decodedPayload = JSON.parse(atob(base64));
        const role = decodedPayload.role; // Supozohet se token ka fushën "role"
  
        localStorage.setItem('authToken', token);
  
        // Ridrejto përdoruesin bazuar në rolin e tij
        if (role === 'DOCTOR') {
          navigate('/doctor/');
        } else if (role === 'NURSE') {
          navigate('/nurse/');
        } else if (role === 'PATIENT') {
          navigate('/patient/');
        } else if (role === 'ADMIN') {
          navigate('/admin/');
        } 
        // else if (role === 'USER') {
        //   navigate('/user/');
        // }
         else {
          navigate('/unauthorized'); // Ose një faqe tjetër default
        }
      } else {
        console.log('No token found in response');
      }
    } catch (error) {
      setErrorMessage('Invalid email or password');
    }
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
        required
      />
      <input
        type="password"
        name="password"
        placeholder="Password"
        value={formData.password}
        onChange={handleChange}
        required
      />
      {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
      <button type="submit">Login</button>
    </form>
  );
};

export default LoginForm;
