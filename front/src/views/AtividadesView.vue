<template>
  <div class="p-6">
    <h1 class="text-2xl font-bold mb-4 text-gray-800 dark:text-white">Lista de Projetos</h1>

    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
      <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" class="px-6 py-3">Nome</th>
            <th scope="col" class="px-6 py-3">Cor</th>
            <th scope="col" class="px-6 py-3">Categoria</th>
            <th scope="col" class="px-6 py-3">Preço</th>
            <th scope="col" class="px-6 py-3">Ação</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="project in projects"
            :key="project.id"
            class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700 border-gray-200"
          >
            <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
              {{ project.name }}
            </th>
            <td class="px-6 py-4">
              {{ project.color }}
            </td>
            <td class="px-6 py-4">
              {{ project.category }}
            </td>
            <td class="px-6 py-4">
              {{ project.price }}
            </td>
            <td class="px-6 py-4">
              <a href="#" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Editar</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const projects = ref([
  { id: 1, name: "Apple MacBook Pro 17", color: "Silver", category: "Laptop", price: "$2999" },
]);
</script>
