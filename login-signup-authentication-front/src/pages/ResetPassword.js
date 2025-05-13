import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { resetPassword } from "../services/requests/auth";
import "./ForgotPassword.scss";

export default function ResetPassword() {
  const location = useLocation();
  const navigate = useNavigate();
  const emailFromState = location.state?.email || "";

  const [formData, setFormData] = useState({
    email: emailFromState,
    verificationCode: "",
    newPassword: "",
    confirmPassword: "",

  });

  const [message, setMessage] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleResetPassword = async () => {
    setLoading(true);
    setMessage(null);
    try {
      await resetPassword(formData);
      setMessage({ type: "success", text: "Password reset successfully." });
      navigate("/login");
    } catch (error) {
      setMessage({ type: "error", text: "Failed to reset password." });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="reset-container">
      <div className="form-box">
        <h2>Reset Password</h2>

        <input
          type="text"
          className="input-field"
          name="verificationCode"
          placeholder="Verification Code"
          value={formData.verificationCode}
          onChange={handleChange}
        />

        <input
          type="password"
          className="input-field"
          name="newPassword"
          placeholder="New Password"
          value={formData.newPassword}
          onChange={handleChange}
        />
        <input
          type="password"
          className="input-field"
          name="confirmPassword"
          placeholder="Confirm Password"
          value={formData.confirmPassword}
          onChange={handleChange}
        />

        <button className="submit-btn" onClick={handleResetPassword} disabled={loading}>
          {loading ? "Resetting..." : "Reset Password"}
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
