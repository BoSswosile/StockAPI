"use client";
import { useState } from 'react';
import { useRouter } from 'next/router';

export default function AddProductPage() {
    const [product, setProduct] = useState({
        name: '',
        price: 0,
        quantity: 0,
        length: 0,
        width: 0,
        height: 0,
        color: ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProduct({ ...product, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        alert('Produit ajouté : ' + JSON.stringify(product));
    };

    return (
        <main className="flex flex-col justify-center items-center h-screen">
            <div className="relative flex max-w-4xl w-full flex-col rounded-10 border border-gray-200 bg-white shadow-md p-8">
                <h4 className="text-2xl font-bold text-navy-700 mb-8">Ajouter un Produit</h4>
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
                            Ajouter
                        </button>
                    </div>
                </form>
            </div>
        </main>
    );
}