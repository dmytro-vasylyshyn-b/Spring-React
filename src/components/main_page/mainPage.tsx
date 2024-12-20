// src/components/main_page/MainPage.tsx
import React from 'react';
import ProductsList from '../productsList';
import { Footer } from '../shared/Footer';

export const MainPage: React.FC = () => {
  return (
    <>
        <ProductsList />
        <Footer />
    </>
  );
};
