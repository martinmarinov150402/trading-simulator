import React from 'react';
import './App.css';
import { Header } from './components/Header';
import { BrowserRouter, Route, Routes } from 'react-router';
import { RegisterPage } from './pages/RegisterPage';
import { LoginPage } from './pages/LoginPage';
import { HomePage } from './pages/HomePage';
import { KrakenServiceContextProvider } from './contexts/kraken-service-context';
import { DashboardPage } from './pages/DashboardPage';

function App() {
  return (
      <BrowserRouter>
        <KrakenServiceContextProvider>
          <Header></Header>
          <Routes>
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/" element={<DashboardPage/>} />
          </Routes>
        </KrakenServiceContextProvider>
      </BrowserRouter>
  );
}

export default App;
