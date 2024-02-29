"use client";
import { useState, useEffect } from 'react';
import { useRouter } from 'next/router';

export default function EditProductPage( { params: { id } }) {

    const [product, setProduct] = useState({
        name: '',
        price: 0,
        quantity: 0,
        length: 0,
        width: 0,
        height: 0,
        color: ''
    });

    useEffect(() => {
        const fakeApiData = [
            { id: 1, name: 'Table de Kevin', price: 100, quantity: 10, length: 100, width: 100, height: 100, color: 'black' },
            { id: 2, name: 'Table de Lora', price: 200, quantity: 20, length: 200, width: 200, height: 200, color: 'white' },
            { id: 3, name: 'Table de Marc', price: 300, quantity: 30, length: 300, width: 300, height: 300, color: 'red' }
        ];

        const productToEdit = fakeApiData.find(p => p.id === parseInt(id));
        if (productToEdit) {
            setProduct(productToEdit);
        }
    }, [id]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProduct({ ...product, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        alert('Produit modifié : ' + JSON.stringify(product));
    };

    return (
        <main className="flex flex-col justify-center items-center h-screen">
            <div className="relative flex max-w-4xl w-full flex-col rounded-10 border border-gray-200 bg-white shadow-md p-8">
                <h4 className="text-2xl font-bold text-navy-700 mb-8">Modifier le Produit</h4>
                <form onSubmit={handleSubmit} className="space-y-6">
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Nom</label>
                        <input
                            type="text"
                            name="name"
                            value={product.name}
                            onChange={handleInputChange}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                            required
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Prix</label>
                        <input
                            type="number"
                            name="price"
                            value={product.price}
                            onChange={handleInputChange}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                            required
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Quantité</label>
                        <input
                            type="number"
                            name="quantity"
                            value={product.quantity}
                            onChange={handleInputChange}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                            required
                        />
                    </div>
                    <div className="grid grid-cols-3 gap-4">
                        <div>
                            <label className="block text-sm font-medium text-gray-700">Longueur</label>
                            <input
                                type="number"
                                name="length"
                                value={product.length}
                                onChange={handleInputChange}
                                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-gray-700">Largeur</label>
                            <input
                                type="number"
                                name="width"
                                value={product.width}
                                onChange={handleInputChange}
                                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-gray-700">Hauteur</label>
                            <input
                                type="number"
                                name="height"
                                value={product.height}
                                onChange={handleInputChange}
                                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                                required
                            />
                        </div>
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Couleur</label>
                        <input
                            type="text"
                            name="color"
                            value={product.color}
                            onChange={handleInputChange}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                            required
                        />
                    </div>
                    <div className="flex justify-end">
                        <button
                            type="submit"
                            className="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                        >
                            Modifier
                        </button>
                    </div>
                </form>
            </div>
        </main>
    );
}
