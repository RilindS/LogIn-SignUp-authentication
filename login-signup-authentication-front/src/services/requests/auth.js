

export const loginUser = async ({ email, password }) => {
    if (email === 'test@example.com' && password === '123456') {
      return { success: true, token: 'fake-jwt-token' };
    } else {
      throw new Error('Invalid credentials');
    }
  };
  
  export const registerUser = async ({ name, email, password }) => {
    return { success: true, message: 'User registered successfully' };
  };
  