"use client"

import { useNavigate } from "react-router-dom"

const HomePage = () => {
  const navigate = useNavigate()

  const handleLogIn = () => {
    navigate("/login")
  }

  const handleSecurityClick = () => {
    alert("Security settings clicked")
    // navigate("/security") ose funksionalitet tjetër në të ardhmen
  }

  const handleResetClick = () => {
    alert("Reset clicked")
    // navigate("/reset") ose funksionalitet tjetër
  }

  const handleForgotPasswordClick = () => {
    alert("Forgot Password clicked")
    // navigate("/forgot-password") ose funksionalitet tjetër
  }

  return (
    <div style={{ minHeight: "100vh", fontFamily: "Arial, sans-serif" }}>
      {/* Hero Section */}
      <div
        style={{
          background: "#2563eb",
          minHeight: "100vh",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          padding: "0 20px",
          position: "relative",
          overflow: "hidden",
          flexDirection: "column",
        }}
      >
        {/* Background Image */}
        <div
          style={{
            position: "absolute",
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundImage: `url("https://images.pexels.com/photos/4197562/pexels-photo-4197562.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")`,
            backgroundSize: "cover",
            backgroundPosition: "center",
            opacity: 0.5,
            zIndex: 0,
          }}
        />

        <div
          style={{
            textAlign: "center",
            color: "white",
            zIndex: 1,
            maxWidth: "600px",
          }}
        >
          <h1 style={{ fontSize: "3.5rem", fontWeight: "bold", marginBottom: "20px" }}>
            Welcome
          </h1>
          <p style={{ fontSize: "1.2rem", marginBottom: "40px", color: "#e0e7ff" }}>
            Access your account and manage your profile securely.
          </p>
          <button
            onClick={handleLogIn}
            style={{
              background: "white",
              color: "#2563eb",
              border: "none",
              padding: "15px 30px",
              fontSize: "1.1rem",
              fontWeight: "bold",
              borderRadius: "50px",
              cursor: "pointer",
              transition: "all 0.3s ease",
              boxShadow: "0 4px 15px rgba(0,0,0,0.2)",
            }}
            onMouseOver={(e) => {
              e.target.style.transform = "translateY(-2px)"
              e.target.style.boxShadow = "0 6px 20px rgba(0,0,0,0.3)"
            }}
            onMouseOut={(e) => {
              e.target.style.transform = "translateY(0)"
              e.target.style.boxShadow = "0 4px 15px rgba(0,0,0,0.2)"
            }}
          >
            LOG IN
          </button>

          {/* Box Options */}
          <div
            style={{
              marginTop: "40px",
              display: "flex",
              justifyContent: "center",
              gap: "20px",
              flexWrap: "wrap",
            }}
          >
            {/* {[
              { label: "Security", onClick: handleSecurityClick },
              { label: "Reset", onClick: handleResetClick },
              { label: "Forgot Password", onClick: handleForgotPasswordClick },
            ].map((item, index) => (
              <div
                key={index}
                onClick={item.onClick}
                style={{
                  backgroundColor: "white",
                  color: "#2563eb",
                  padding: "15px 25px",
                  borderRadius: "10px",
                  cursor: "pointer",
                  fontWeight: "bold",
                  transition: "all 0.3s ease",
                  boxShadow: "0 2px 10px rgba(0,0,0,0.1)",
                }}
                onMouseOver={(e) => {
                  e.currentTarget.style.transform = "scale(1.05)"
                  e.currentTarget.style.boxShadow = "0 4px 20px rgba(0,0,0,0.2)"
                }}
                onMouseOut={(e) => {
                  e.currentTarget.style.transform = "scale(1)"
                  e.currentTarget.style.boxShadow = "0 2px 10px rgba(0,0,0,0.1)"
                }}
              >
                {item.label}
              </div>
            ))} */}
          </div>
        </div>
      </div>
    </div>
  )
}

export default HomePage
