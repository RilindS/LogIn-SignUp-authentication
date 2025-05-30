import React, { useState, useEffect } from 'react';

const CityForm = ({ onSubmit, initialData }) => {
  const [name, setName] = useState('');
  const [id, setId] = useState(null);

  useEffect(() => {
    if (initialData) {
      setName(initialData.name);
      setId(initialData.id);
    } else {
      setName('');
      setId(null);
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ id, name });
    setName('');
    setId(null);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="City name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />
      <button type="submit">{id ? 'Update' : 'Create'}</button>
    </form>
  );
};

export default CityForm;
