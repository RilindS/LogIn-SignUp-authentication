import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../../services/requests/auth';
import './Form.css'; 

const LoginForm = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const [errorMessage, setErrorMessage] = useState(null);
  const navigate = useNavigate();

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
        const role = decodedPayload.role;

        localStorage.setItem('authToken', token);

        if (role === 'DOCTOR') {
          navigate('/doctor/');
        } else if (role === 'NURSE') {
          navigate('/nurse/');
        } else if (role === 'PATIENT') {
          navigate('/patient/');
        } else if (role === 'ADMIN') {
          navigate('/admin/');
        } else {
          navigate('/unauthorized');
        }
      } else {
        console.log('No token found in response');
      }
    } catch (error) {
      setErrorMessage('Invalid email or password');
    }
  };

  return (
    <div className="form-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Login</h2>
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
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginForm;
