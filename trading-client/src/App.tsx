import React from 'react';
import './App.css';
import { Header } from './components/Header';
import { RegisterForm } from './components/RegisterForm';

function App() {
  return (
    <div>
      <Header></Header>
      <RegisterForm/>
    </div>
  );
}

export default App;
