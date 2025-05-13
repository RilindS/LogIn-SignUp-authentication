import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../../services/requests/auth';
import './LoginRegisterPage.scss';


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

      if (response.twoFactorRequired) {
        navigate('/verify-otp', {
          state: {
            email: formData.email,
            userId: response.userId
          }
        });
        return;
      }

      const token = response.token;
      if (token) {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const decodedPayload = JSON.parse(atob(base64));
        const role = decodedPayload.role;

        localStorage.setItem('authToken', token);

        if (role === 'USER') {
          navigate('/user/');
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

  const handleForgotPassword = () => {
    navigate('/forget-password');
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

      <div style={{ marginTop: '10px', textAlign: 'center' }}>
        <button type="button" onClick={handleForgotPassword} style={{
          background: 'none',
          border: 'none',
          color: '#007bff',
          textDecoration: 'underline',
          cursor: 'pointer',
          padding: 0,
          fontSize: '14px'
        }}>
          Forgot Password?
        </button>
      </div>
    </form>
  );
};

export default LoginForm;
