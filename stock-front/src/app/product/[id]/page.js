"use client";
import { useState, useEffect } from 'react';
import {useRouter, useSearchParams} from 'next/navigation'
import OnlyStoreKeeper from "@/components/OnlyStoreKeeper";

export default function EditProductPage( {params} ) {

    const searchParams = useSearchParams()
    const id = params.id;
    const router = useRouter();

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
        // Assuming you have a way to fetch the product details based on the id
        // For demonstration, we'll use a mock fetch
        console.log(id)
        const fetchProductDetails = async () => {
            const response = await fetch(`http://localhost:8080/product/${id}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                    }
                });
            console.log(response);

            const data = await response.json();
            setProduct(data); // Assuming the response is an array with a single product
        };

        if (id) {
            fetchProductDetails();
        }
    }, [id]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProduct({ ...product, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const body = {
            id: id,
            refId: product.refId,
            name: product.name,
            price: product.price,
            quantity: product.quantity,
            length: product.length,
            width: product.width,
            height: product.height,
            color: product.color
        };

        try {
            const response = await fetch(`http://localhost:8080/product/update/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                },
                body: JSON.stringify(body)
            });

            if (response.ok) {
                router.push('/');
            } else {
                alert('Erreur lors de la modification du produit');
            }
        } catch (error) {
            console.error('Erreur de réseau:', error);
            alert('Erreur de réseau');
        }
    };

    return (
        <main className="flex flex-col justify-center items-center h-screen">
            <OnlyStoreKeeper />
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
