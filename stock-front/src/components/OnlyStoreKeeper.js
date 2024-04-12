'use client'

import {useRouter} from "next/navigation";

const OnlyStoreKeeper = () => {
    const router = useRouter();

    if (typeof window == 'undefined') {
        return  null;
    }

    // check if the user have the role in localstorage
    const jwt = localStorage.getItem('jwt');


    if (!jwt) {
        localStorage.setItem('message', "Vous n'êtes pas connecté");
        router.push('/auth/login');
    }

    if(localStorage.getItem('role') !== 'ROLE_STOREKEEPER' && localStorage.getItem('role') !== 'ROLE_ADMINISTRATOR') {
        localStorage.setItem('message', "Vous n'êtes pas autorisé à accéder à cette page");
        router.push('/');
    }

    return null;

};

export default OnlyStoreKeeper;