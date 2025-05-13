import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { forgotPassword } from "../services/requests/auth";
import "./ForgotPassword.scss";

export default function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleSendCode = async () => {
    setIsLoading(true);
    setMessage(null);
    try {
      await forgotPassword(email);
      navigate("/reset-password", { state: { email } });
    } catch (error) {
      setMessage({ type: "error", text: "Failed to send verification code." });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="forgot-container">
      <div className="form-box">
        <h2>Forgot Password</h2>
        <p>Enter your email to receive a password reset code.</p>

        <input
          type="email"
          className="input-field"
          placeholder="Email address"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <button className="submit-btn" onClick={handleSendCode} disabled={isLoading}>
          {isLoading ? "Sending..." : "Send Reset Code"}
        </button>

        {message && (
          <p className={message.type === "error" ? "error-text" : "success-text"}>
            {message.text}
          </p>
        )}
      </div>
    </div>
  );
}
