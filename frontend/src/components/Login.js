import React, { useState } from 'react';
import api from '../services/api';
import { setToken } from '../auth';
import { useNavigate } from 'react-router-dom';

export default function Login() {
  const [form, setForm] = useState({ username: '', password: '' });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post('/api/auth/login', form);
      setToken(res.data.token);
      navigate('/articles');
    } catch (err) {
      alert('Login failed');
    }
  };

  return (
    <div className="container mt-5">
      <h3>Login</h3>
      <form onSubmit={handleSubmit}>
        <input className="form-control mb-2" placeholder="Username"
          onChange={(e) => setForm({ ...form, username: e.target.value })} />
        <input className="form-control mb-2" type="password" placeholder="Password"
          onChange={(e) => setForm({ ...form, password: e.target.value })} />
        <button className="btn btn-primary">Login</button>
      </form>
    </div>
  );
}
