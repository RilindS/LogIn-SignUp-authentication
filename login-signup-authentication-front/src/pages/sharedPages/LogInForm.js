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


  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await loginUser(formData);
      const token = response.token; 
  
    } catch (error) {
      setErrorMessage('Invalid email or password');
    }
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input
        type="Email"
        name="Email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
        required
      />
      <input
        type="Password"
        name="Password"
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
