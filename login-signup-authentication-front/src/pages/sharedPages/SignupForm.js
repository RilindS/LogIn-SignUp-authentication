import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { registerUser } from '../../services/requests/auth';
import './Form.css';

const SignupForm = () => {
  const [formData, setFormData] = useState({ name: '', email: '', password: '' });
  const [errorMessage, setErrorMessage] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await registerUser(formData);
      navigate('/login');
    } catch (error) {
      setErrorMessage('Registration failed');
    }
  };

  return (
    <div className="form-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Sign Up</h2>
        <input
          type="text"
          name="name"
          placeholder="Full Name"
          value={formData.name}
          onChange={handleChange}
          required
        />
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
        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
};

export default SignupForm;
