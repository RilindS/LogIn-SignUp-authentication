import { useFormik } from 'formik';
import { useEffect, useState } from 'react';
import * as Yup from 'yup';
import { registerUser } from '../../services/requests/auth';
import {
  getAllCities,
} from '../../services/requests/cityService';
import './LoginRegisterPage1.scss';

const RegisterFormAdmin = ({ onRegisterComplete }) => {
  const [cities, setCities] = useState([]);

  useEffect(() => {
    const fetchCities = async () => {
      try {
        const data = await getAllCities();
        setCities(data);
      } catch (error) {
        console.error('Failed to fetch cities', error);
      }
    };

    fetchCities();
  }, []);

  const formik = useFormik({
    initialValues: {
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      phoneNumber: '',
      imageUrl: '',
      cityId: '',
      status: 'ACTIVE',
      role: 'USER',
    },
    validationSchema: Yup.object({
      firstName: Yup.string().required('First name is required'),
      lastName: Yup.string().required('Last name is required'),
      email: Yup.string().email('Invalid email address').required('Email is required'),
      password: Yup.string().min(6, 'Password must be at least 6 characters').required('Password is required'),
      phoneNumber: Yup.string(),
      cityId: Yup.string().required('City is required'),
    }),
    onSubmit: async (values, { setSubmitting, resetForm }) => {
      try {
        const response = await registerUser(values);
        console.log('Registration successful:', response);
        resetForm();
        onRegisterComplete();
      } catch (error) {
        console.error('Error during registration:', error);
      } finally {
        setSubmitting(false);
      }
    },
  });

  return (
    <form onSubmit={formik.handleSubmit}>
      <input
        type="text"
        name="firstName"
        placeholder="First Name"
        value={formik.values.firstName}
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
      />
      {formik.touched.firstName && formik.errors.firstName && <div>{formik.errors.firstName}</div>}

      <input
        type="text"
        name="lastName"
        placeholder="Last Name"
        value={formik.values.lastName}
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
      />
      {formik.touched.lastName && formik.errors.lastName && <div>{formik.errors.lastName}</div>}

      <input
        type="email"
        name="email"
        placeholder="Email"
        value={formik.values.email}
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
      />
      {formik.touched.email && formik.errors.email && <div>{formik.errors.email}</div>}

      <input
        type="password"
        name="password"
        placeholder="Password"
        value={formik.values.password}
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
      />
      {formik.touched.password && formik.errors.password && <div>{formik.errors.password}</div>}

      <input
        type="tel"
        name="phoneNumber"
        placeholder="Phone Number"
        value={formik.values.phoneNumber}
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
      />
      {formik.touched.phoneNumber && formik.errors.phoneNumber && <div>{formik.errors.phoneNumber}</div>}

      <input
        type="text"
        name="imageUrl"
        placeholder="Image URL"
        value={formik.values.imageUrl}
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
      />
      {formik.touched.imageUrl && formik.errors.imageUrl && <div>{formik.errors.imageUrl}</div>}

      <select
        name="cityId"
        value={formik.values.cityId}
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
      >
        <option value="">Select City</option>
        {cities.map((city) => (
          <option key={city.id} value={city.id}>
            {city.name}
          </option>
        ))}
      </select>
      {formik.touched.cityId && formik.errors.cityId && <div>{formik.errors.cityId}</div>}

      <button type="submit" disabled={formik.isSubmitting}>Register</button>
    </form>
  );
};

export default RegisterFormAdmin;
