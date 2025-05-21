import React from 'react';
import './App.css';
import { Header } from './components/Header';
import { BrowserRouter, Route, Routes } from 'react-router';
import { RegisterPage } from './pages/RegisterPage';
import { LoginPage } from './pages/LoginPage';
import { HomePage } from './pages/HomePage';

function App() {
  return (
      <BrowserRouter>
        <Header></Header>
        <Routes>
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage/>}/>
          <Route path="/" element={<HomePage/>} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
