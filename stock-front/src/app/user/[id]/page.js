"use client";
import { useState, useEffect } from 'react';
import {useRouter, useSearchParams} from 'next/navigation'
import OnlyAdmin from "@/components/OnlyAdmin";
import FloatingNotification from "@/components/FloatingNotification";
import Link from "next/link";

export default function ChangeUserRole( {params} ) {

    const searchParams = useSearchParams()
    const id = params.id;
    const router = useRouter();
    const [roles, setRoles] = useState([]);

    useEffect(() => {
        const fetchProductDetails = async () => {
            const response = await fetch(`http://localhost:8080/roles`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                    }
                });

            if (!response.ok) {
                throw new Error('Erreur lors de la récupération des rôles');
            }

            const data = await response.json();
            setRoles(data);
        };
        fetchProductDetails();
    }, []);

    function handleSubmit(e) {
        e.preventDefault();
        const role = e.target.role.value;
        console.log(role);
        const body = {
            roleName: role
        };
        console.log(body);
        fetch(`http://localhost:8080/user/setRole/${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            },
            body: JSON.stringify(body)
        })
            .then((response) => {
                if (response.ok) {
                    localStorage.setItem('message', "Rôle modifié avec succès");
                    router.push('/user');
                } else {
                    localStorage.setItem('message', "Rôle non modifié à cause d'une erreur");
                }
            })
            .catch((error) => {
                console.error('Erreur de réseau:', error);
                localStorage.setItem('message', "Erreur réseau");
            });
    }

    return (
        <main className="flex flex-col justify-center items-center h-screen">
            <OnlyAdmin />
            <FloatingNotification />
            <div className="relative flex max-w-4xl w-full flex-col rounded-10 border border-gray-200 bg-white shadow-md p-8">
                <h4 className="text-2xl font-bold text-navy-700 mb-8">{`Role de l\'utilisateur`}npm</h4>
                <form onSubmit={handleSubmit} className="space-y-6">
                    <div className="flex flex-col">

                        <label htmlFor="name" className="text-sm font-medium text-gray-700">
                            Passer à
                        </label>
                        <select
                            id="role"
                            name="role"
                            autoComplete="role"
                            className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                        >
                            {roles.map((role) => (
                                <option key={role.id} value={role.roleName}>{role.roleName}</option>
                            ))}
                        </select>
                    </div>
                    <div className="flex justify-end">
                        <Link href="/user" className="text-sm mr-5 font-medium text-blue-600 hover:text-blue-500">
                            Retour
                        </Link>
                        <button
                            type="submit"
                            className="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                        >
                            Modifier le rôle
                        </button>
                    </div>
                </form>
            </div>
        </main>
    );
}
