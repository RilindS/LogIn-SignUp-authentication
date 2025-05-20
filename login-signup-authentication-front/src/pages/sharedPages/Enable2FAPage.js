import React, { useEffect, useState } from 'react';
import { getUserData } from '../../services/requests/auth';
import { toggle2FA } from '../../services/requests/auth';

const Enable2FAPage = () => {
  const [user, setUser] = useState(null);
  const [twoFAEnabled, setTwoFAEnabled] = useState(false);
  const [statusMessage, setStatusMessage] = useState('');

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const userData = await getUserData();
        setUser(userData);
        setTwoFAEnabled(userData.twoFactorEnabled || false); // make sure backend sends this flag
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };

    fetchUser();
  }, []);

  const handleToggle = async () => {
    if (!user) return;

    const newValue = !twoFAEnabled;

    try {
      await toggle2FA(user.id, newValue);
      setTwoFAEnabled(newValue);
      setStatusMessage(`Two-factor authentication is now ${newValue ? 'enabled' : 'disabled'}.`);
    } catch (error) {
      setStatusMessage('Failed to update 2FA setting.');
    }
  };

  return (
    <div style={{ padding: '2rem' }}>
      <h2>Two-Factor Authentication</h2>
      <p>Status: <strong>{twoFAEnabled ? 'Enabled' : 'Disabled'}</strong></p>
      <button onClick={handleToggle}>
        {twoFAEnabled ? 'Turn OFF' : 'Turn ON'}
      </button>
      {statusMessage && <p style={{ marginTop: '1rem' }}>{statusMessage}</p>}
    </div>
  );
};

export default Enable2FAPage;
