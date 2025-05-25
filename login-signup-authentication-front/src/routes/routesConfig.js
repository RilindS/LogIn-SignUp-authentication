
//import EmailPage from '../pages/entity/email/EmailPage';
import HomePage from "../pages/Home/HomePage";
import Enable2FAPage from "../pages/sharedPages/Enable2FAPage";
import RegisterForm from "../pages/sharedPages/RegisterForm";
import RegisterFormAdmin from "../pages/sharedPages/RegisterFormAdmin";
import ShowUser from "../pages/sharedPages/ShowUser";


export const AdminRoutes = [
    { path: "/email", element: <HomePage /> },
    { path: "/register", element: <RegisterForm /> },
    { path: "/register-admin", element: <RegisterFormAdmin /> },
    { path:"/enable-2fa", element: <Enable2FAPage />},
        { path:"/profile", element: <ShowUser />},


   
];
export const UserRoutes = [
    { path:"/enable-2fa", element: <Enable2FAPage />},
           { path:"/profile", element: <ShowUser />},
            
];

