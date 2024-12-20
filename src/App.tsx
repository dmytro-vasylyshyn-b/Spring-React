// src/App.tsx
import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, Routes } from 'react-router-dom';
import { CreateProduct } from './components/products/CreateProducts';
import { MainPage } from './components/main_page/mainPage';
import { RegistrationComponent } from './components/registration/RegistrationComponent';

const App: React.FC = () => {
  return (
    <>
      <Routes>
        <Route path="/createProduct" element={<CreateProduct />} />
        <Route path="/shop" element={<MainPage />} />
        <Route path="/register" element={<RegistrationComponent />} />

      </Routes>
    </>
  );
};

export default App;
