import api from "../axios";

export const registerUser = async (userData) => {
  try {
    const response = await api.post('/users/create', userData);
    return response.data; 
  } catch (error) {
    console.error('Registration error:', error);
    throw error;
  }
};

export const loginUser = async (loginData) => {
  try {
    const response = await api.post('/auth', loginData);
    return response.data;
  } catch (error) {
    console.error('Login error:', error);
    throw error;
  }
};

export const verifyOtp = async (otpData) => {
  try {
    const response = await api.post('/verify-otp', otpData);
    return response.data;
  } catch (error) {
    console.error('OTP verification error:', error);
    throw error;
  }
};
 export const forgotPassword = async (email) => {
  try {
    const response = await api.post("/forgot-password", { email });
    return response.data;
  } catch (error) {
    console.error('Forgot password error:', error);
    throw error;
  }
};

export const resetPassword = async (data) => {
  try {
    const response = await api.post("/reset-password", data);
    return response.data;
  } catch (error) {
    console.error('Reset password error:', error);
    throw error;
  }
};



//this is for /me endppoint 
export const getUserData = async () => {
  const token = localStorage.getItem("authToken");
  if (token) {
    try {
      const response = await fetch("http://localhost:8080/api/users/me", {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });
      if (response.ok) {
        return await response.json(); // Parse JSON response
      } else {
        throw new Error(`Failed to fetch data: ${response.status}`);
      }
    } catch (error) {
      console.error("Error in API request:", error);
      throw error;
    }
  } else {
    throw new Error("No token found");
  }
};

export const toggle2FA = async (userId, enabled) => {
  const token = localStorage.getItem("authToken");
  try {
    const response = await fetch(`http://localhost:8080/api/user/${userId}/2fa?enabled=${enabled}`, {
      method: "PUT",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Failed to update 2FA settings");
    }

    return await response.text();
  } catch (error) {
    console.error("Error toggling 2FA:", error);
    throw error;
  }
};


