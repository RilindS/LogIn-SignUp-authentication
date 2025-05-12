import {
  faBed,
  faBoxes,
  faCalendar,
  faCity,
  faComments,
  faEnvelope,
  faHospital,
  faSignOutAlt,
  faTachometerAlt,
  faUser,
  faUserCircle,
  faUserMd,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Link, useNavigate } from "react-router-dom";
  //import "./Sidebar.scss";
  
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
            <Link to="/admin/allpatient">
              <FontAwesomeIcon icon={faUser} /> Manage Patients
            </Link>
          </li>
          <li>
            <Link to="/admin/doctor">
              <FontAwesomeIcon icon={faUserMd} /> Manage Doctors
            </Link>
          </li>
          <li>
            <Link to="/admin/allnurse">
              <FontAwesomeIcon icon={faUserMd} /> Manage Nurse
            </Link>
          </li>
          <li>
            <Link to="/admin/allRoom">
              <FontAwesomeIcon icon={faHospital} /> Manage Room
            </Link>
          </li>
          <li>
            <Link to="/admin/allInventory">
              <FontAwesomeIcon icon={faBoxes} /> Manage Inventory
            </Link>
          </li>
          <li>
            <Link to="/admin/city">
              <FontAwesomeIcon icon={faCity} /> Manage City
            </Link>
          </li>
          <li>
            <Link to="/admin/room/patients">
              <FontAwesomeIcon icon={faBed} /> See Patients in Room
            </Link>
          </li>
          <li>
            <Link to="/admin/feedback/nurse">
              <FontAwesomeIcon icon={faComments} /> See Feedback for Nurse
            </Link>
          </li>
          <li>
            <Link to="/admin/feedback/doctor">
              <FontAwesomeIcon icon={faComments} /> See Feedback for Doctors
            </Link>
          </li>
          <li>
            <Link to="/admin/vacation/nurse">
              <FontAwesomeIcon icon={faCalendar} /> See Vacation for Nurse
            </Link>
          </li>
          <li>
            <Link to="/admin/vacation/doctor">
              <FontAwesomeIcon icon={faCalendar} /> See Vacation for Doctors
            </Link>
          </li>
          <li>
            <Link to="/admin/Myprofile">
              <FontAwesomeIcon icon={faUserCircle} /> My Profile
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
  