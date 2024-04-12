'use client'

import { useEffect, useState } from 'react';
import Link from "next/link";

const NavButtons = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        // Vérifie si le token JWT est présent dans le localStorage
        const token = localStorage.getItem('jwt');
        setIsLoggedIn(!!token);
    }, []);

    const handleLogout = () => {
        // Supprime le token JWT du localStorage
        localStorage.removeItem('jwt');
        // Met à jour l'état pour refléter la déconnexion
        setIsLoggedIn(false);
    };

    return (
        <div className="flex justify-center items-center space-x-4 my-20">
            <Link href="/" className="text-sm mr-2 font-medium text-blue-600 hover:text-blue-500">
                Produits
            </Link>
            {localStorage.getItem('role') && localStorage.getItem('role') == "ROLE_ADMINISTRATOR" && <Link href="/user" className="text-sm mr-5 font-medium text-blue-600 hover:text-blue-500">Utilisateurs</Link>}
            {!isLoggedIn && (
                <>
                    <Link href="/auth/login" className="relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                        Connexion
                    </Link>
                    <Link href="/auth/register" className="relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                        Inscrire
                    </Link>
                </>
            )}
            {isLoggedIn && (
                <Link href="/auth/logout" className="relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                    Deconnexion
                </Link>
            )}
        </div>
    );
};

export default NavButtons;