import React from 'react';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ roles, component: Component }) => {
  const token = localStorage.getItem('authToken');

  if (!token) {
    return <Navigate to="/" />;
  }

  // Dekodoni token për të marrë rolin
  const decodedToken = JSON.parse(atob(token.split('.')[1]));
  const userRole = decodedToken?.role;

  // Kontrolloni nëse `allowedRoles` është i vlefshëm
  if (!roles || !Array.isArray(roles)) {
    console.error('Roles are undefined or not an array');
    return <Navigate to="/unauthorized" />;
  }

  // Kontrolloni nëse roli i përdoruesit përputhet
  if (roles.includes(userRole)) {
    return <Component />;
  }

  return <Navigate to="/unauthorized" />;
};

export default PrivateRoute;
