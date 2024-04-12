'use client'

import { useEffect, useState } from "react";

const FloatingNotification = () => {
    const [message, setMessage] = useState('');
    const [showNotification, setShowNotification] = useState(false);

    useEffect(() => {
        if (typeof window == 'undefined') {
            return  null;
        }
        const storedMessage = localStorage.getItem('message');
        if (storedMessage) {
            setMessage(storedMessage);
            setShowNotification(true);
        }
    }, []);

    const handleClose = () => {
        if (typeof window == 'undefined') {
            return  null;
        }
        setShowNotification(false);
        localStorage.removeItem('message'); // Supprime le message de localStorage
    };

    if (!showNotification) {
        return null;
    }

    return (
        <div className="fixed top-0 right-0 m-4 p-4 bg-blue-500 text-white rounded-md shadow-lg z-50">
            {message}
            <button onClick={handleClose} className="ml-2">X</button>
        </div>
    );
};

export default FloatingNotification;