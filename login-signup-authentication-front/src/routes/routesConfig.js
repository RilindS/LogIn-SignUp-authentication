
//import EmailPage from '../pages/entity/email/EmailPage';
import HomePage from "../pages/Home/HomePage";
import RegisterForm from "../pages/sharedPages/RegisterForm";
import RegisterFormAdmin from "../pages/sharedPages/RegisterFormAdmin";
import Enable2FAPage from "../pages/sharedPages/Enable2FAPage";


export const AdminRoutes = [
    { path: "/email", element: <HomePage /> },
    { path: "/register", element: <RegisterForm /> },
    { path: "/register-admin", element: <RegisterFormAdmin /> },
    { path:"/enable-2fa", element: <Enable2FAPage />},

   
];
export const UserRoutes = [
    { path:"/enable-2fa", element: <Enable2FAPage />},
   
];

