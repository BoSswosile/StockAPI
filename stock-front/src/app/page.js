"use client";
import { useState, useEffect } from 'react';
import Link from "next/link";

import FloatingNotification from "@/components/FloatingNotification";
import NavButtons from "@/components/NavButtons";

export default function Home() {
  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [isNotViewer, setIsNotViewer] = useState(false);

  useEffect(() => {
    const role = localStorage.getItem('role');
    if (role == 'ROLE_ADMINISTRATOR' || role == 'ROLE_STOREKEEPER') {
      setIsNotViewer(true);
    }
  }, []);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/product');
      if (!response.ok) {
        throw new Error('Erreur lors de la récupération des produits');
      }
      const data = await response.json();
      setProducts(data);
    } catch (error) {
      console.error('Erreur lors de la récupération des produits:', error);
    }
  };

  // Fonction pour gérer la suppression d'un produit
  const handleDelete = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/product/delete/${id}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error('Erreur lors de la suppression du produit');
      }

      fetchData();

    } catch (error) {
      console.error('Erreur lors de la suppression du produit:', error);
    }
  };

  const filteredProducts = products.filter(product =>
      product.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
      <main className="flex flex-col justify-center items-center h-[100vh]">
        <FloatingNotification />
        <NavButtons />
        <div className="flex justify-center w-full px-4 mb-4">
          <div className="w-auto relative">
            <input
                type="text"
                placeholder="Rechercher un produit..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="w-full p-2 pl-10 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
            />
            {/* Icône de recherche */}
            <div className="absolute left-0 top-0 flex items-center h-full pl-3">
              <svg className="w-4 h-4 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                   xmlns="http://www.w3.org/2000/svg">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2}
                      d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
              </svg>
            </div>
          </div>
        </div>
        <div
            className="relative flex max-w-[90vw] h-[430px] w-full flex-col rounded-[10px] border-[1px] border-gray-200 bg-white bg-clip-border shadow-md shadow-[#F3F3F3] dark:border-[#ffffff33] dark:!bg-navy-800 dark:text-white dark:shadow-none"
        >
          <div
              className="flex h-fit w-full items-center justify-between rounded-t-2xl bg-white px-4 pb-[20px] pt-4 shadow-2xl shadow-gray-100 dark:!bg-navy-700 dark:shadow-none"
          >
            <h4 className="text-lg font-bold text-navy-700 dark:text-white">
              Produits
            </h4>
            { isNotViewer &&
              <Link href={`/product/new`}
                    className="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">+
              </Link>
            }
          </div>
          <div className="w-full overflow-x-scroll px-4 md:overflow-x-hidden">
            <table role="table" className="w-full min-w-[500px] overflow-x-scroll">
              <thead>
              <tr role="row">
                <th
                    colSpan="1"
                    role="columnheader"
                    title="Toggle SortBy"
                >
                  <div
                      className="flex items-center justify-between pb-2 pt-4 text-start uppercase tracking-wide text-gray-600 sm:text-xs lg:text-xs"
                  >
                    Nom
                  </div>
                </th>
                <th
                    colSpan="1"
                    role="columnheader"
                    title="Toggle SortBy"
                >
                  <div
                      className="flex items-center justify-between pb-2 pt-4 text-start uppercase tracking-wide text-gray-600 sm:text-xs lg:text-xs"
                  >
                    Stock
                  </div>
                </th>
                <th
                    colSpan="1"
                    role="columnheader"
                    title="Toggle SortBy"
                >
                  <div
                      className="flex items-center justify-between pb-2 pt-4 text-start uppercase tracking-wide text-gray-600 sm:text-xs lg:text-xs"
                  >
                    Dimensions (l*L*H)
                  </div>
                </th>
                <th
                    colSpan="1"
                    role="columnheader"
                    title="Toggle SortBy"
                >
                  <div
                      className="flex items-center justify-between pb-2 pt-4 text-start uppercase tracking-wide text-gray-600 sm:text-xs lg:text-xs"
                  >
                    Couleur
                  </div>
                </th>
                <th
                    colSpan="1"
                    role="columnheader"
                    title="Toggle SortBy"
                >
                  <div
                      className="flex items-center justify-between pb-2 pt-4 text-start uppercase tracking-wide text-gray-600 sm:text-xs lg:text-xs"
                  >
                    Prix
                  </div>
                </th>
                { isNotViewer &&
                    <th
                        colSpan="1"
                        role="columnheader"
                        title="Toggle SortBy"
                    >
                      <div
                          className="flex items-center justify-between pb-2 pt-4 text-start uppercase tracking-wide text-gray-600 sm:text-xs lg:text-xs"
                      >
                        Action
                      </div>
                    </th>
                }
              </tr>
              </thead>
              <tbody role="rowgroup" className="px-4">
              {filteredProducts.length > 0 && filteredProducts.map((product) => (
                  <tr role="row" key={product.id}>
                    <td className="py-3 text-sm" role="cell">
                      <p
                          className="text-sm font-medium text-navy-700 dark:text-white"
                      >
                        {product.name}
                      </p>
                    </td>
                    <td className="py-3 text-sm" role="cell">
                      <p className="text-md font-medium text-gray-600 dark:text-white">
                        {product.quantity}
                      </p>
                    </td>
                    <td className="py-3 text-sm" role="cell">
                      <p className="text-md font-medium text-gray-600 dark:text-white">
                        {product.length}*{product.width}*{product.height}
                      </p>
                    </td>
                    <td className="py-3 text-sm" role="cell">
                      <p className="text-md font-medium text-gray-600 dark:text-white">
                        {product.color}
                      </p>
                    </td>
                    <td className="py-3 text-sm" role="cell">
                      <p className="text-md font-medium text-gray-600 dark:text-white">
                        {product.price} €
                      </p>
                    </td>
                    { isNotViewer &&
                      <td className="py-3 text-sm" role="cell">
                        <Link href={`/product/${product.id}`}
                              className="text-white bg-gradient-to-r from-teal-400 via-teal-500 to-teal-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-teal-300 dark:focus:ring-teal-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">Modifier
                        </Link>
                        <button type="button"
                                onClick={() => handleDelete(product.id)}
                                className="text-white bg-gradient-to-r from-red-400 via-red-500 to-red-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">-
                        </button>
                      </td>
                    }
                  </tr>
              ))}
              </tbody>
            </table>
          </div>
        </div>
        <p className="font-normal text-navy-700 mt-20 mx-auto w-max">Projet par Yohan Moran, Jérémie Le Bihan, Grosieux
          Clément</p>
      </main>
  );
}