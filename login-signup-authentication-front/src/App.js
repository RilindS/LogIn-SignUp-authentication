import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import PrivateRoute from "./components/PrivateRoute";
import AdminLayout from "./layouts/AdminLayout";
import OtpVerificationPage from "./pages/sharedPages/OtpVerificationPage";

import ForgotPassword from "./pages/ForgotPassword";
import HomePage from "./pages/Home/HomePage";
import ResetPassword from "./pages/ResetPassword";
import LoginRegisterPage from "./pages/sharedPages/LoginAndRegisterPage";
import Unauthorized from "./pages/sharedPages/Unauthorized";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginRegisterPage />} />
        <Route path="/forget-password" element={<ForgotPassword />} />
                <Route path="/reset-password" element={<ResetPassword />} />


        <Route path="/" element={<HomePage />} />
        <Route path="/unauthorized" element={<Unauthorized />} />
        <Route path="/verify-otp" element={<OtpVerificationPage />} />


        <Route path="/admin/*" element={<PrivateRoute roles={['ADMIN']} component={AdminLayout} />} />
        {/* <Route path="/doctor/*" element={<PrivateRoute roles={['DOCTOR']} component={DoctorLayout} />} />
        <Route path="/patient/*" element={<PrivateRoute roles={['PATIENT']} component={PatientLayout} />} />
        <Route path="/nurse/*" element={<PrivateRoute roles={['NURSE']} component={NurseLayout} />} /> */}
      </Routes>
    </Router>
  );
};

export default App;
