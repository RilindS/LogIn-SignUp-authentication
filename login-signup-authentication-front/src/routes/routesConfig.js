
//import EmailPage from '../pages/entity/email/EmailPage';
import CityPage from "../pages/city/CityPage";
import HomePage from "../pages/Home/HomePage";
import Enable2FAPage from "../pages/sharedPages/Enable2FAPage";
import RegisterForm from "../pages/sharedPages/RegisterForm";
import RegisterFormAdmin from "../pages/sharedPages/RegisterFormAdmin";
import ShowUser from "../pages/sharedPages/ShowUser";
import UserPage from "../pages/sharedPages/UserPage";


export const AdminRoutes = [
    { path: "/email", element: <HomePage /> },
    { path: "/register", element: <RegisterForm /> },
    { path: "/register-admin", element: <RegisterFormAdmin /> },
    { path:"/enable-2fa", element: <Enable2FAPage />},
        { path:"/profile", element: <ShowUser />},
           { path:"/city", element: <CityPage />},
        { path:"/profile", element: <ShowUser />},
                { path:"/users", element: <UserPage />},



   
];
export const UserRoutes = [
    { path:"/enable-2fa", element: <Enable2FAPage />},
           { path:"/profile", element: <ShowUser />},
            
];

