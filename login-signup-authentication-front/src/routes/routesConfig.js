
//import EmailPage from '../pages/entity/email/EmailPage';
import HomePage from "../pages/Home/HomePage";
import RegisterForm from "../pages/sharedPages/RegisterForm";
import RegisterFormAdmin from "../pages/sharedPages/RegisterFormAdmin";


export const AdminRoutes = [
    { path: "/email", element: <HomePage /> },
    { path: "/register", element: <RegisterForm /> },
    { path: "/register-admin", element: <RegisterFormAdmin /> },


   
];
export const UserRoutes = [
    
   
];

