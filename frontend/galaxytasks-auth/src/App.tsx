import './App.css'
import {Routes, Route } from 'react-router-dom';
import Register from './components/register'
import Login from './components/login';
import Home from './components/home';

function App() {

  return (
    <div className="conteneur">
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="*" element={<Home />} />
      </Routes>
    </div>
  );
}

export default App
