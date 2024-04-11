'use client'

import { useSearchParams } from 'next/navigation'
import {useEffect, useState} from "react";

const FloatingNotification = () => {
    const [message, setMessage] = useState('');
    const [showNotification, setShowNotification] = useState(false);

    useEffect(() => {
        const storedMessage = localStorage.getItem('message');
        if (storedMessage) {
            setMessage(storedMessage);
            setShowNotification(true);

            const timer = setTimeout(() => {
                setShowNotification(false);
                localStorage.removeItem('message'); // Supprime le message de localStorage
            }, 5000); // 3000 millisecondes = 3 secondes

            // Nettoyage de l'effet de bord pour éviter les fuites de mémoire
            return () => clearTimeout(timer);
        }
    }, []);

    if (!showNotification) {
        return null;
    }

    return (
        <div className="fixed top-0 right-0 m-4 p-4 bg-blue-500 text-white rounded-md shadow-lg z-50">
            {message}
        </div>
    );

};

export default FloatingNotification;
