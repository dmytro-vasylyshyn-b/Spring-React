import React, { useState, useEffect } from 'react';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import { ICategory } from '../../models/CategoriesModel';

export function CreateProduct() {
    const [productName, setProductName] = useState('');
    const [productDescription, setProductDescription] = useState('');
    const [productPrice, setProductPrice] = useState('');
    const [productStock, setProductStock] = useState('');
    const [photoUrl, setPhotoUrl] = useState<File | null>(null);
    const [categories, setCategories] = useState<ICategory[]>([]);
    const [selectedCategory, setSelectedCategory] = useState<number | null>(null);

    useEffect(() => {
        fetch('http://localhost:8083/api/categories')
            .then(response => response.json())
            .then(data => setCategories(data))
            .catch((error) => console.error('Error fetching categories:', error));
    }, []);

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();

        if (!productName || !productDescription || !productPrice || !productStock || !selectedCategory || !photoUrl) {
            console.log("All fields must be filled!");
            return;
        }

        const formData = new FormData();
        formData.append('productDTO', new Blob([JSON.stringify({
            productName,
            productDescription,
            productPrice: parseFloat(productPrice),
            productStock: parseInt(productStock),
            productCategory: { categoryId: selectedCategory }
        })], { type: "application/json" }));
        formData.append('file', photoUrl);

        fetch('http://localhost:8083/api/products', {
            method: 'POST',
            body: formData,
        })
            .then(response => response.json())
            .then(data => console.log('Product created:', data))
            .catch(error => console.error('Error:', error));
    };

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files) {
            setPhotoUrl(event.target.files[0]);
        }
    };

    return (
        <div style={{ marginTop: 150, marginLeft: 300, marginRight: 300 }}>
            <h1 style={{ marginBottom: 30 }}>Create your product</h1>
            <Form onSubmit={handleSubmit}>
                <InputGroup className="mb-3">
                    <InputGroup.Text>Product Name</InputGroup.Text>
                    <Form.Control value={productName} onChange={(e) => setProductName(e.target.value)} />
                </InputGroup>

                <InputGroup className="mb-3">
                    <InputGroup.Text>Description</InputGroup.Text>
                    <Form.Control value={productDescription} onChange={(e) => setProductDescription(e.target.value)} />
                </InputGroup>

                <InputGroup className="mb-3">
                    <InputGroup.Text>Product Price</InputGroup.Text>
                    <Form.Control type="number" value={productPrice} onChange={(e) => setProductPrice(e.target.value)} />
                </InputGroup>

                <InputGroup className="mb-3">
                    <InputGroup.Text>Product Stock</InputGroup.Text>
                    <Form.Control type="number" value={productStock} onChange={(e) => setProductStock(e.target.value)} />
                </InputGroup>

                <InputGroup className="mb-3">
                    <InputGroup.Text>Category</InputGroup.Text>
                    <Form.Control as="select" value={selectedCategory || ''} onChange={(e) => setSelectedCategory(Number(e.target.value))}>
                        <option value="">Select Category</option>
                        {categories.map((category) => (
                            <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                        ))}
                    </Form.Control>
                </InputGroup>

                <InputGroup className="mb-3">
                    <InputGroup.Text>Upload Image</InputGroup.Text>
                    <Form.Control type="file" onChange={handleFileChange} />
                </InputGroup>

                <Button type="submit">Create Product</Button>
            </Form>
        </div>
    );
}
