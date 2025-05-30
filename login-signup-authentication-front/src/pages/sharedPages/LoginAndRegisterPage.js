import { useState } from 'react';
import LoginForm from './LogInForm';
import './LoginRegisterPage.scss';
import RegisterForm from './RegisterForm';

const LoginRegisterPage = () => {
  const [isRegister, setIsRegister] = useState(false);

  const toggleForm = () => setIsRegister((prev) => !prev);

  return (
    <div className="auth-page">
      <div className="auth-image"></div>
      <div className="auth-container">
        
        <h1>{isRegister ? 'Register' : 'Login'}</h1>
        {isRegister ? (
          <RegisterForm onRegisterComplete={toggleForm} />
        ) : (
          <LoginForm />
        )}
        {/* <button className="toggle-button" onClick={toggleForm}>
          {isRegister ? 'Already have an account? Login' : "Don't have an account? Register"}
        </button> */}
      </div>
      
    </div>
  );
};

export default LoginRegisterPage;