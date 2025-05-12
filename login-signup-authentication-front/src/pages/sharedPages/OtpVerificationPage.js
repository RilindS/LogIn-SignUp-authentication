import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { verifyOtp } from '../../services/requests/auth';

const OtpVerificationPage = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { email } = location.state || {}; // vjen nga LoginForm

  const [otp, setOtp] = useState('');
  const [errorMessage, setErrorMessage] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await verifyOtp({ email, otp });
      const token = response.token;

      localStorage.setItem('authToken', token);

      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const decodedPayload = JSON.parse(atob(base64));
      const role = decodedPayload.role;

      if (role === 'USER') {
        navigate('/user/');
      } else if (role === 'ADMIN') {
        navigate('/admin/');
      } else {
        navigate('/unauthorized');
      }

    } catch (error) {
      setErrorMessage('Invalid or expired OTP');
    }
  };

  return (
    <div>
      <h2>Enter OTP</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter OTP"
          value={otp}
          onChange={(e) => setOtp(e.target.value)}
          required
        />
        {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        <button type="submit">Verify</button>
      </form>
    </div>
  );
};

export default OtpVerificationPage;
