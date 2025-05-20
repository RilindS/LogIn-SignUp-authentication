import { useEffect, useState } from "react";
import { getUserData } from '../../services/requests/auth';
import "./ShowUser.scss";

const ShowUser = () => {
  const [user, setUser] = useState(null); 
  const [error, setError] = useState(null); 

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const userData = await getUserData();
        console.log("Fetched user data:", userData);
        setUser(userData); 
      } catch (err) {
        console.error("Error fetching user data:", err);
        setError("Failed to load user data.");
      }
    };

    fetchUser();
  }, []);

  return (
    <div className="show-user">
      <h2>User Details</h2>
      {error ? (
        <p className="error">{error}</p>
      ) : user ? (
        <table className="user-table">
          <tbody>
            <tr>
              <th>First Name</th>
              <td>{user.firstName}</td>
            </tr>
            <tr>
              <th>Last Name</th>
              <td>{user.lastName}</td>
            </tr>
            <tr>
              <th>Email</th>
              <td>{user.email}</td>
            </tr>
            <tr>
              <th>Phone Number</th>
              <td>{user.phoneNumber}</td>
            </tr>
            <tr>
              <th>Role</th>
              <td>{user.role}</td>
            </tr>
            <tr>
              <th>Status</th>
              <td>{user.status}</td>
            </tr>
          </tbody>
        </table>
      ) : (
        <p>Loading user data...</p>
      )}
    </div>
  );
};

export default ShowUser;
