import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { IProduct } from '../../models/productsModel'; 
import defaultPhoto from '../../../src/image.png';

interface ProductCardProps {
  product: IProduct;
}

function ProductCard({ product }: ProductCardProps) {
  const [showFullDescription, setShowFullDescription] = useState(false);
  const [productImage, setProductImage] = useState<string>(defaultPhoto);

  useEffect(() => {
    if (typeof product.photoUrl === 'string' && product.photoUrl.trim()) {
      setProductImage(`http://localhost:8083${product.photoUrl}`);
      console.log(`http://localhost:8083${product.photoUrl}`)
    } else {
      setProductImage(defaultPhoto); // Use default image if photoUrl is not valid
    }
  }, [product.photoUrl]);

  const toggleDescription = () => {
    setShowFullDescription((prev) => !prev);
  };

  const isDescriptionLong = product.productDescription.length > 50;
  const displayedDescription = showFullDescription
    ? product.productDescription
    : `${product.productDescription.substring(0, 50)}...`;

  return (
    <Card style={{ width: '18rem', margin: '10px' }}>
      <Card.Img 
        variant="top" 
        src={productImage} 
        alt="Product image"
        onError={() => setProductImage(defaultPhoto)} // Fallback to default if image fails to load
      />
      <Card.Body>
        <Card.Title style={{ textAlign: 'center' }}>
          {product.productName}
        </Card.Title>
        <Card.Text>
          {displayedDescription}
          {isDescriptionLong && (
            <Button variant="link" onClick={toggleDescription} style={{ padding: 0 }}>
              {showFullDescription ? 'Show less' : 'Show more'}
            </Button>
          )}
        </Card.Text>
        <Card.Text><b>Price: $</b>{product.productPrice}</Card.Text>
        <Button variant="primary">Add to Cart</Button>
      </Card.Body>
    </Card>
  );
}

export default ProductCard;
