import { useEffect, useState } from 'react';
import { getUserData, toggle2FA } from '../../services/requests/auth';

const Enable2FAPage = () => {
  const [user, setUser] = useState(null);
  const [twoFAEnabled, setTwoFAEnabled] = useState(false);
  const [statusMessage, setStatusMessage] = useState('');
  const [loading, setLoading] = useState(false);

  const fetchUser = async () => {
    try {
      const userData = await getUserData();
      setUser(userData);
      setTwoFAEnabled(userData.twoFactorEnabled || false);
    } catch (error) {
      console.error('Error fetching user data:', error);
    }
  };

  useEffect(() => {
    fetchUser();
  }, []);

  const handleChange = async (event) => {
    const newValue = event.target.value === 'enabled';

    if (!user || newValue === twoFAEnabled) return;

    try {
      setLoading(true);
      await toggle2FA(user.id, newValue);
      setStatusMessage(`Two-factor authentication is now ${newValue ? 'enabled' : 'disabled'}.`);

      // Rifresko të dhënat e përdoruesit pas përditësimit
      await fetchUser();
    } catch (error) {
      console.error(error);
      setStatusMessage('Failed to update 2FA setting.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: '2rem' }}>
      <h2>Two-Factor Authentication</h2>
      <p>Status: <strong>{twoFAEnabled ? 'Enabled' : 'Disabled'}</strong></p>

      <fieldset disabled={loading}>
        <div onChange={handleChange}>
          <label>
            <input
              type="radio"
              value="enabled"
              checked={twoFAEnabled}
              disabled={twoFAEnabled}
            />
            Enable
          </label>

          <label style={{ marginLeft: '1rem' }}>
            <input
              type="radio"
              value="disabled"
              checked={!twoFAEnabled}
              disabled={!twoFAEnabled}
            />
            Disable
          </label>
        </div>
      </fieldset>

      {statusMessage && <p style={{ marginTop: '1rem' }}>{statusMessage}</p>}
    </div>
  );
};

export default Enable2FAPage;
