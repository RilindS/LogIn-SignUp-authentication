import React from 'react';
import { Routes } from 'react-router-dom';
import { UserRoutes } from '../routes/routesConfig';
import generateRoutes from '../utils/GenerateRoutes';
import UserSidebar from '../sidebar/UserSidebar';

//import "./Layout.scss";

const UserLayout = () => (
  <div className="layout">+
    <UserSidebar />
    <div className="content">
      <Routes>{generateRoutes(UserRoutes)}</Routes>
    </div>
  </div>
);

export default UserLayout;