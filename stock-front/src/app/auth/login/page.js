"use client";
import {useEffect, useState} from 'react';
import Link from 'next/link';
import { useRouter } from 'next/navigation'
import FloatingNotification from "@/components/FloatingNotification";

export default function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const router = useRouter();

    useEffect(() => {
        const jwt = localStorage.getItem('jwt');
        if (jwt) {
            localStorage.setItem('message', "Vous êtes déjà connecté");
            router.push('/');
        }
    }, []);


    async function fetchUserRole(token) {
        let role;

        try {
            const response = await fetch('http://localhost:8080/user/getUserRole', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            role = data[0].roleName;
        } catch (error) {
            console.error('There has been a problem with your fetch operation:', error);
        }

        return role;
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, password }),
        });

        if (response.ok) {
            const jwt = await response.text();
            console.log(jwt);
            localStorage.setItem('jwt', jwt);
            const role = await fetchUserRole(jwt);
            localStorage.setItem('role', role);
            localStorage.setItem('message', "Vous êtes maintenant connecté");
            console.log("jwt", jwt)
            router.push('/');
        } else {
            console.error('Email ou mot de passe incorrect');
        }
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
            <FloatingNotification />
            <div className="max-w-md w-full space-y-8">
                <div>
                    <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
                        Connectez-vous
                    </h2>
                </div>
                <form className="mt-8 space-y-3" onSubmit={handleSubmit}>
                    <input type="hidden" name="remember" value="true"/>
                    <div className="rounded-md shadow-sm -space-y-px">
                        <div>
                            <label htmlFor="email-address" className="block mt-4 text-sm font-medium text-gray-700">
                                Email
                            </label>
                            <input
                                id="email-address"
                                name="email"
                                type="email"
                                autoComplete="email"
                                required
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                                placeholder="admin@gmail.com"
                            />
                        </div>
                        <div>
                            <label htmlFor="password" className="block mt-4 text-sm font-medium text-gray-700">
                                Mot de passe
                            </label>
                            <input
                                id="password"
                                name="password"
                                type="password"
                                autoComplete="current-password"
                                required
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                                placeholder="admin123"
                            />
                        </div>
                    </div>

                    <div>
                        <button
                            type="submit"
                            className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                        >
                            Connexion
                        </button>
                    </div>
                </form>
                <div className="text-center mt-1">
                    <Link href="/" className="text-indigo-500 hover:underline">
                        Page produit
                    </Link>
                </div>
                <div className="text-center mt-1">
                    <p>Pas de compte?</p>
                    <Link href="/auth/register" className="text-indigo-500 hover:underline">
                        Inscription
                    </Link>
                </div>
            </div>
        </div>
    );
}
