import {
  faEnvelope,
  faSignOutAlt,
  faTachometerAlt,
  faUser,
  faShieldAlt
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link, useNavigate } from "react-router-dom";
import "./Sidebar.scss";
  
  const AdminSidebar = () => {
    const navigate = useNavigate();
  
    const handleLogout = () => {
      localStorage.removeItem("token");
      localStorage.removeItem("authToken");
      navigate("/login");
    };
  
    return (
      <div className="sidebar">
        <ul>
          <li>
            <Link to="/admin/dashboard">
              <FontAwesomeIcon icon={faTachometerAlt} /> Dashboard
            </Link>
          </li>
          <li>
            <Link to="/admin/email">
              <FontAwesomeIcon icon={faEnvelope} /> Send Email
            </Link>
          </li>
            <li>
            <Link to="/admin/profile">
              <FontAwesomeIcon icon={faUser} /> My Profile
            </Link>
          </li>
         <li>
            <Link to="/admin/register">
              <FontAwesomeIcon icon={faTachometerAlt} /> Register User
            </Link>
          </li>
          <li>
            <Link to="/admin/register-admin">
              <FontAwesomeIcon icon={faTachometerAlt} /> Register Admin
            </Link>
          </li>
          <li>
            <Link to="/admin/enable-2fa">
              <FontAwesomeIcon icon={faShieldAlt} /> Enable 2FA
            </Link>
          </li>
          <li>
            <Link to="/" onClick={handleLogout}>
              <FontAwesomeIcon icon={faSignOutAlt} /> Log Out
            </Link>
          </li>
        </ul>
      </div>
    );
  };
  
  export default AdminSidebar;
  