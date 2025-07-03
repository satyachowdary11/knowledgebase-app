import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import ArticleList from './components/ArticleList';
import ProtectedRoute from './pages/ProtectedRoute';
import Navbar from './components/Navbar';
import ArticleForm from './components/ArticleForm';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/articles" element={
          <ProtectedRoute>
             <Navbar />
            <ArticleList />
                 <ArticleForm />
          </ProtectedRoute>
        } />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
