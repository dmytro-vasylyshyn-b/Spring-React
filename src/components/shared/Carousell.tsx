import React, { useEffect, useState } from 'react';
import Carousel from 'react-bootstrap/Carousel';
import axios from 'axios';
import { IProduct } from '../../models/productsModel';
import ExampleCarouselImage from '../../image.png';

export function Carousell() {
  const [discountedProducts, setDiscountedProducts] = useState<IProduct[]>([]);

  // Function to fetch discounted products
  const fetchDiscountedProducts = async () => {
    try {
      const response = await axios.get<IProduct[]>('http://localhost:8083/api/products');
      
      // Filter products with discount > 50%
      const productsWithDiscount = response.data.filter(
        (product) => product.productDiscount && product.productDiscount.discountPercentage > 50
      );
      setDiscountedProducts(productsWithDiscount);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  useEffect(() => {
    fetchDiscountedProducts();
  }, []);

  return (
    <Carousel>
      {discountedProducts.map((product) => {
        // Check if a photoUrl exists and use it if valid, otherwise use the default image
        const productImage = product.photoUrl ? `http://localhost:8083${product.photoUrl}` : ExampleCarouselImage;

        return (
          <Carousel.Item key={product.productId}>
            <img
              src={productImage}
              alt={product.productName}
              className="d-block w-100"
              onError={(e) => (e.currentTarget.src = ExampleCarouselImage)} // Fallback to default if image fails to load
            />
            <Carousel.Caption>
              <h3>{product.productName}</h3>
              <p>{product.productDescription}</p>
              {product.productDiscount && (
                <p>
                  <strong>Discount:</strong> {product.productDiscount.discountPercentage}% off
                </p>
              )}
            </Carousel.Caption>
          </Carousel.Item>
        );
      })}
    </Carousel>
  );
}

export default Carousell;
