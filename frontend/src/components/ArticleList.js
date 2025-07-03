import React, { useEffect, useState } from 'react';
import { Container, TextField, Button, Select, MenuItem, Typography, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import api from '../services/api';
import { isAdminOrEditor } from '../auth';

export default function ArticleList() {
  const [articles, setArticles] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [keyword, setKeyword] = useState('');
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('');

  // Fetch categories
  useEffect(() => {
    api.get('/categories')
      .then(res => setCategories(res.data))
      .catch(() => alert("Failed to load categories"));
  }, []);

  // Fetch articles
  const fetchArticles = () => {
    let url = `/articles?page=${page}&size=5`;

    if (keyword) {
      url = `/articles/search?keyword=${keyword}&page=${page}&size=5`;
    } else if (selectedCategory) {
      url = `/articles/by-category/${selectedCategory}?page=${page}&size=5`;
    }

    api.get(url)
      .then(res => {
        setArticles(res.data.content);
        setTotalPages(res.data.totalPages);
      })
      .catch(() => alert("Failed to load articles"));
  };

  useEffect(() => {
    fetchArticles();
    // eslint-disable-next-line
  }, [page, keyword, selectedCategory]);

  // Handle delete
  const handleDelete = async (id) => {
    if (!window.confirm("Delete this article?")) return;
    try {
      await api.delete(`/articles/${id}`);
      fetchArticles();
    } catch {
      alert("Delete failed");
    }
  };

  return (
    <Container maxWidth="md" className="mt-4">
      <Typography variant="h4" gutterBottom>Articles</Typography>

      <TextField
        fullWidth
        label="Search by title"
        variant="outlined"
        className="mb-3"
        value={keyword}
        onChange={(e) => setKeyword(e.target.value)}
      />

      <Select
        fullWidth
        value={selectedCategory}
        onChange={(e) => setSelectedCategory(e.target.value)}
        displayEmpty
        className="mb-3"
      >
        <MenuItem value=""><em>All Categories</em></MenuItem>
        {categories.map(cat => (
          <MenuItem key={cat.id} value={cat.name}>{cat.name}</MenuItem>
        ))}
      </Select>

      {articles.map(article => (
        <div key={article.id} className="border rounded p-3 mb-3">
          <Typography variant="h6">{article.title}</Typography>
          <Typography variant="subtitle2" color="text.secondary">By {article.authorName}</Typography>
          <Typography variant="body2">{article.content.substring(0, 150)}...</Typography>

          {isAdminOrEditor() && (
            <div className="mt-2">
              <IconButton color="primary" size="small">
                <EditIcon />
              </IconButton>
              <IconButton color="error" size="small" onClick={() => handleDelete(article.id)}>
                <DeleteIcon />
              </IconButton>
            </div>
          )}
        </div>
      ))}

      <div className="d-flex justify-content-between align-items-center mt-4">
        <Button
          variant="contained"
          color="secondary"
          disabled={page === 0}
          onClick={() => setPage(page - 1)}
        >Previous</Button>

        <Typography>Page {page + 1} of {totalPages}</Typography>

        <Button
          variant="contained"
          color="secondary"
          disabled={page + 1 >= totalPages}
          onClick={() => setPage(page + 1)}
        >Next</Button>
      </div>
    </Container>
  );
}
