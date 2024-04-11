"use client";
import Link from 'next/link';
import { useRouter } from 'next/navigation'
import {useEffect} from "react";

export default function Logout() {

    const router = useRouter();

    useEffect(() => {
        const jwt = localStorage.getItem('jwt');
        if (jwt) {
            localStorage.removeItem('jwt');
        }else {
            localStorage.setItem('message', "Vous n'êtes pas connecté");
            router.push('/auth/login');
        }
    }, []);

    return (
        <div class="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
            <div class="max-w-md w-full space-y-8">
                <div>
                    <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
                        Déconnexion
                    </h2>
                </div>
                <div class="mt-8 space-y-6">
                    <div class="rounded-md shadow-sm -space-y-px">
                        <div>
                            <p class="mt-4 text-sm font-medium text-gray-700">
                                Vous êtes maintenant déconnecté
                            </p>
                        </div>
                    </div>
                    <div>
                        <Link href="/auth/login" class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-navy-600 hover:bg-navy-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-navy-500">
                                Se connecter
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}
