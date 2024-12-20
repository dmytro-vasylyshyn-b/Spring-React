import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Pagination from 'react-bootstrap/Pagination';
import ProductCard from './shared/ProductCard';
import { IProduct } from '../models/productsModel';
import { ICategory } from '../models/CategoriesModel';
import { Carousell } from './shared/Carousell';
import { Form, Row, Col, Button } from 'react-bootstrap';

const ProductsList: React.FC = () => {
  const [products, setProducts] = useState<IProduct[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>('');
  const [category, setCategory] = useState<string>('');
  const [minPrice, setMinPrice] = useState<string>('');
  const [maxPrice, setMaxPrice] = useState<string>('');
  const [categories, setCategories] = useState<ICategory[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);

  const productsPerPage = 8;

  const fetchCategories = async () => {
    try {
      const response = await axios.get('http://localhost:8083/api/categories');
      if (Array.isArray(response.data)) {
        setCategories(response.data);
      } else {
        console.error('Expected an array of categories');
      }
    } catch (error) {
      console.error('Error fetching categories', error);
    }
  };

  const fetchProducts = async () => {
    try {
      const params: { [key: string]: string | undefined } = {};
      if (searchTerm) params.name = searchTerm;
      if (category) params.categoryId = category;
      if (minPrice) params.minPrice = minPrice;
      if (maxPrice) params.maxPrice = maxPrice;

      const response = await axios.get<IProduct[]>('http://localhost:8083/api/products', { params });
      setProducts(response.data);
      setCurrentPage(1);
    } catch (error) {
      console.error('Error fetching products', error);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  useEffect(() => {
    fetchProducts();
  }, [searchTerm, category, minPrice, maxPrice]);

  const indexOfLastProduct = currentPage * productsPerPage;
  const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
  const currentProducts = products.slice(indexOfFirstProduct, indexOfLastProduct);

  const totalPages = Math.ceil(products.length / productsPerPage);

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  return (
    <>
      <Carousell />
      <div className="container my-4">
        <h1 className="text-center mb-4">Products</h1>
        <Form className="mb-4">
          <Row className="align-items-center">
            <Col xs={12} md={4} className="mb-2 mb-md-0">
              <Form.Control
                type="text"
                placeholder="Search by name"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="form-control"
              />
            </Col>

            <Col xs={6} md={2} className="mb-2 mb-md-0">
              <Form.Control
                type="number"
                placeholder="Min price"
                value={minPrice}
                onChange={(e) => setMinPrice(e.target.value)}
                className="form-control"
              />
            </Col>

            <Col xs={6} md={2} className="mb-2 mb-md-0">
              <Form.Control
                type="number"
                placeholder="Max price"
                value={maxPrice}
                onChange={(e) => setMaxPrice(e.target.value)}
                className="form-control"
              />
            </Col>

            <Col xs={12} md={3} className="mb-2 mb-md-0">
              <Form.Select
                value={category}
                onChange={(e) => setCategory(e.target.value)}
                className="form-select"
              >
                <option value="">All Categories</option>
                {categories.map((category) => (
                  <option key={category.categoryId} value={category.categoryId}>
                    {category.categoryName}
                  </option>
                ))}
              </Form.Select>
            </Col>

            <Col xs={12} md="auto">
              <Button variant="primary" onClick={() => fetchProducts()}>
                Search
              </Button>
            </Col>
          </Row>
        </Form>

        <div className="d-flex flex-wrap justify-content-center">
          {currentProducts.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))}
        </div>

        <Pagination className="justify-content-center mt-4">
          <Pagination.First onClick={() => handlePageChange(1)} disabled={currentPage === 1} />
          <Pagination.Prev onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 1} />
          {[...Array(totalPages)].map((_, index) => (
            <Pagination.Item
              key={index + 1}
              active={currentPage === index + 1}
              onClick={() => handlePageChange(index + 1)}
            >
              {index + 1}
            </Pagination.Item>
          ))}
          <Pagination.Next onClick={() => handlePageChange(currentPage + 1)} disabled={currentPage === totalPages} />
          <Pagination.Last onClick={() => handlePageChange(totalPages)} disabled={currentPage === totalPages} />
        </Pagination>
      </div>
    </>
  );
};

export default ProductsList;
