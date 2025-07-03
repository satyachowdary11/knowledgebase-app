import React, { useState } from 'react';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

export default function ArticleForm() {
  const [form, setForm] = useState({ title: '', content: '' });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post('/articles?username=admin', form); // Or fetch username from token
      alert('Article created!');
      navigate('/articles');
    } catch (err) {
      alert('Create failed');
    }
  };

  return (
    <div className="container mt-4">
      <h3>Create Article</h3>
      <form onSubmit={handleSubmit}>
        <input className="form-control mb-2" placeholder="Title"
          value={form.title}
          onChange={(e) => setForm({ ...form, title: e.target.value })} />

        <textarea className="form-control mb-2" placeholder="Content"
          rows={6}
          value={form.content}
          onChange={(e) => setForm({ ...form, content: e.target.value })} />

        <button className="btn btn-success">Submit</button>
      </form>
    </div>
  );
}
