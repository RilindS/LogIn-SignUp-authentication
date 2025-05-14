//import "./home.css";
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
    const navigate = useNavigate();

  const handleLogIn = () => {
    navigate('/login');
  };
  return (
    <div className="home-page">
   
      <div className="home-page-content">
        {/* <img className="home-page-image" src={DoctorImage} alt="" /> */}
        <div className="home-page-text">
          <h2 className="home-page-text-title">Health care</h2>
          <h2 className="home-page-text-title-second">For hole family</h2>
          <p className="home-page-text-content">
            In the healthcare sector, service excellence is the ability of a
            hospital as a healthcare service provider to consistently deliver
            high-quality care
          </p>
        </div>
        <div style={{ marginTop: '10px', textAlign: 'center' }}>
        <button type="button" onClick={handleLogIn} style={{
          background: 'none',
          border: 'none',
          color: '#007bff',
          textDecoration: 'underline',
          cursor: 'pointer',
          padding: 0,
          fontSize: '14px'
        }}>
         LOG IN
        </button>
      </div>
      </div>

    </div>
  );
};

export default HomePage;

