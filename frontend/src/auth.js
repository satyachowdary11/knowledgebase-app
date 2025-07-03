import { jwtDecode } from 'jwt-decode';

export const setToken = (token) => {
  localStorage.setItem("token", token);
};

export const getToken = () => {
  return localStorage.getItem("token");
};

export const removeToken = () => {
  localStorage.removeItem("token");
};

export const getRole = () => {
  const token = getToken();
  if (!token) return null;

  try {
    const decoded = jwtDecode(token);
    return decoded?.role || null;
  } catch (e) {
    return null;
  }
};

export const isAdminOrEditor = () => {
  const role = getRole();
  return role === 'ADMIN' || role === 'EDITOR';
};
