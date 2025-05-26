"use client"

import { useNavigate } from "react-router-dom"

const HomePage = () => {
  const navigate = useNavigate()

  const handleLogIn = () => {
    navigate("/login")
  }

  const services = [
    {
      title: "Family Medicine",
      description:
        "Comprehensive primary care for patients of all ages, from routine check-ups to chronic disease management.",
      icon: "👨‍⚕️",
    },
    {
      title: "Emergency Care",
      description:
        "24/7 emergency medical services with state-of-the-art equipment and experienced medical professionals.",
      icon: "🚑",
    },
    {
      title: "Pediatric Care",
      description:
        "Specialized healthcare services for infants, children, and adolescents in a child-friendly environment.",
      icon: "👶",
    },
    {
      title: "Preventive Care",
      description: "Regular health screenings, vaccinations, and wellness programs to keep your family healthy.",
      icon: "🛡️",
    },
    {
      title: "Specialist Consultations",
      description: "Access to a network of specialists including cardiology, orthopedics, and internal medicine.",
      icon: "🩺",
    },
    {
      title: "Telemedicine",
      description:
        "Virtual consultations and remote monitoring for convenient healthcare from the comfort of your home.",
      icon: "💻",
    },
  ]

  return (
    <div style={{ minHeight: "100vh", fontFamily: "Arial, sans-serif" }}>
      {/* Hero Section */}
      <div
        style={{
          background: "#2563eb",
          opacity: "1",
          minHeight: "100vh",
          display: "flex",
          alignItems: "center",
          padding: "0 20px",
          position: "relative",
          overflow: "hidden",
        }}
      >
        {/* Background Pattern */}
        <div
          style={{
            position: "absolute",
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundImage: `url("https://images.pexels.com/photos/4197562/pexels-photo-4197562.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")`,
            opacity: 0.5,
          }}
        />

        <div
          style={{
            maxWidth: "1200px",
            margin: "0 auto",
            display: "grid",
            gridTemplateColumns: "1fr 1fr",
            gap: "60px",
            alignItems: "center",
            position: "relative",
            zIndex: 1,
          }}
        >
          {/* Text Content */}
          <div style={{ color: "white" }}>
            <h1
              style={{
                fontSize: "3.5rem",
                fontWeight: "bold",
                marginBottom: "10px",
                lineHeight: "1.2",
              }}
            >
              Health care
            </h1>
            <h2
              style={{
                fontSize: "3rem",
                fontWeight: "600",
                marginBottom: "30px",
                color: "#e0e7ff",
              }}
            >
              For whole family
            </h2>
            <p
              style={{
                fontSize: "1.2rem",
                lineHeight: "1.6",
                marginBottom: "40px",
                color: "#e0e7ff",
                maxWidth: "500px",
              }}
            >
              In the healthcare sector, service excellence is the ability of a hospital as a healthcare service provider
              to consistently deliver high-quality care for every member of your family.
            </p>
            <button
              onClick={handleLogIn}
              style={{
                background: "white",
                color: "#667eea",
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
          </div>

          {/* Hero Image */}
          <div style={{ textAlign: "center" }}>
            <img
              src="https://images.pexels.com/photos/4021775/pexels-photo-4021775.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
              alt="Healthcare professionals"
              style={{
                width: "100%",
                maxWidth: "500px",
                height: "auto",
                borderRadius: "20px",
                boxShadow: "0 20px 40px rgba(0,0,0,0.3)",
              }}
            />
          </div>
        </div>
      </div>

      {/* Services Section */}
      <div
        style={{
          padding: "80px 20px",
          background: "#f8fafc",
        }}
      >
        <div
          style={{
            maxWidth: "1200px",
            margin: "0 auto",
          }}
        >
          <div style={{ textAlign: "center", marginBottom: "60px" }}>
            <h2
              style={{
                fontSize: "2.5rem",
                fontWeight: "bold",
                color: "#1e293b",
                marginBottom: "20px",
              }}
            >
              Our Healthcare Services
            </h2>
            <p
              style={{
                fontSize: "1.2rem",
                color: "#64748b",
                maxWidth: "600px",
                margin: "0 auto",
              }}
            >
              Comprehensive medical care designed to meet all your family's health needs
            </p>
          </div>

          <div
            style={{
              display: "grid",
              gridTemplateColumns: "repeat(auto-fit, minmax(350px, 1fr))",
              gap: "30px",
            }}
          >
            {services.map((service, index) => (
              <div
                key={index}
                style={{
                  background: "white",
                  padding: "30px",
                  borderRadius: "15px",
                  boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
                  transition: "all 0.3s ease",
                  cursor: "pointer",
                }}
                onMouseOver={(e) => {
                  e.currentTarget.style.transform = "translateY(-5px)"
                  e.currentTarget.style.boxShadow = "0 10px 25px rgba(0,0,0,0.15)"
                }}
                onMouseOut={(e) => {
                  e.currentTarget.style.transform = "translateY(0)"
                  e.currentTarget.style.boxShadow = "0 4px 6px rgba(0,0,0,0.1)"
                }}
              >
                <div
                  style={{
                    fontSize: "3rem",
                    marginBottom: "20px",
                  }}
                >
                  {service.icon}
                </div>
                <h3
                  style={{
                    fontSize: "1.5rem",
                    fontWeight: "bold",
                    color: "#1e293b",
                    marginBottom: "15px",
                  }}
                >
                  {service.title}
                </h3>
                <p
                  style={{
                    color: "#64748b",
                    lineHeight: "1.6",
                    fontSize: "1rem",
                  }}
                >
                  {service.description}
                </p>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Call to Action Section */}
      <div
        style={{
          background: "linear-gradient(135deg, #1e293b 0%, #334155 100%)",
          padding: "60px 20px",
          textAlign: "center",
        }}
      >
        <div
          style={{
            maxWidth: "800px",
            margin: "0 auto",
            color: "white",
          }}
        >
          <h2
            style={{
              fontSize: "2.5rem",
              fontWeight: "bold",
              marginBottom: "20px",
            }}
          >
            Ready to Get Started?
          </h2>
          <p
            style={{
              fontSize: "1.2rem",
              marginBottom: "30px",
              color: "#e2e8f0",
            }}
          >
            Join thousands of families who trust us with their healthcare needs
          </p>
          <button
            onClick={handleLogIn}
            style={{
              background: "#667eea",
              color: "white",
              border: "none",
              padding: "15px 40px",
              fontSize: "1.1rem",
              fontWeight: "bold",
              borderRadius: "50px",
              cursor: "pointer",
              transition: "all 0.3s ease",
            }}
            onMouseOver={(e) => {
              e.target.style.background = "#5a67d8"
              e.target.style.transform = "translateY(-2px)"
            }}
            onMouseOut={(e) => {
              e.target.style.background = "#667eea"
              e.target.style.transform = "translateY(0)"
            }}
          >
            Get Started Today
          </button>
        </div>
      </div>
    </div>
  )
}

export default HomePage
