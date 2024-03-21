"use client";
import { useState } from 'react';
import Link from 'next/link'

export default function LoginPage() {

    return (<div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
        <div className="max-w-md w-full space-y-8">
            <div>
                <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
                    Connectez-vous
                </h2>
            </div>
            <form className="mt-8 space-y-6" action="#" method="POST">
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
            <div className="text-center mt-4">
                <p>Pas de compte?</p>
                <Link href="/auth/register" className="text-indigo-500 hover:underline">
                    Inscription
                </Link>
            </div>
        </div>
    </div>);
}
