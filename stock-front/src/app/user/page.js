"use client";
import { useState, useEffect } from 'react';
import Link from "next/link";

import FloatingNotification from "@/components/FloatingNotification";
import NavButtons from "@/components/NavButtons";
import OnlyAdmin from "@/components/OnlyAdmin";

export default function Users() {
  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/user/getAllUsers', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('jwt')}`
        }
      });
      if (!response.ok) {
        throw new Error('Erreur lors de la récupération des utilisateurs');
      }
      const data = await response.json();
      setUsers(data);
    } catch (error) {
      console.error('Erreur lors de la récupération des utilisateurs:', error);
    }
  };

  const filteredUsers = users.filter(user =>
      user.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
      <main className="flex flex-col justify-center items-center h-[100vh]">
        <OnlyAdmin />
        <FloatingNotification />
        <NavButtons />
        <div className="flex justify-center w-full px-4 mb-4">
          <div className="w-auto relative">
            <input
                type="text"
                placeholder="Rechercher un utilisateur..."
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
              Utilisateurs
            </h4>
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
                    Rôle
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
                    Action
                  </div>
                </th>
              </tr>
              </thead>
              <tbody role="rowgroup" className="px-4">
              {filteredUsers.length > 0 && filteredUsers.map((user) => (
                  <tr role="row" key={user.id}>
                    <td className="py-3 text-sm" role="cell">
                      <p
                          className="text-sm font-medium text-navy-700 dark:text-white"
                      >
                        {user.name}
                      </p>
                    </td>
                    <td className="py-3 text-sm" role="cell">
                      <p className="text-md font-medium text-gray-600 dark:text-white">
                        {user.roles[0]['roleName']}
                      </p>
                    </td>
                    <td className="py-3 text-sm" role="cell">
                      <Link href={`/user/${user.id}`} className={'text-blue-500'}>
                          Changer le rôle
                      </Link>
                    </td>
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