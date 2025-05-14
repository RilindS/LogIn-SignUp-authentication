import {
    faSignOutAlt,
    faTachometerAlt
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link, useNavigate } from "react-router-dom";
import "./Sidebar.scss";
  
  const UserSidebar = () => {
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
            <Link to="/user/dashboard">
              <FontAwesomeIcon icon={faTachometerAlt} /> Dashboard
            </Link>
          </li>
          <li>
            <Link to="/user/enable-2fa">
              <FontAwesomeIcon icon={faTachometerAlt} /> Enable 2FA
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
  
  export default UserSidebar;
  