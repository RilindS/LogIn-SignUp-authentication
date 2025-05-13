import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginForm from './pages/sharedPages/LogInForm';
import SignupForm from './pages/sharedPages/SignupForm';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/signup" replace />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/signup" element={<SignupForm />} />
        {/* Add other routes here */}
      </Routes>
    </Router>
  );
}

export default App;
