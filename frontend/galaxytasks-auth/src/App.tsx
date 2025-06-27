import './App.css'

import {Routes, Route } from 'react-router-dom';
import Register from './components/register'
import Login from './components/login';

function App() {
  return (
    <div className="conteneur">
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="*" element={<Login />} />
      </Routes>
    </div>
  );
}

export default App
