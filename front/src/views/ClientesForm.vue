<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import clienteService from '@/services/clienteService';
import Swal from 'sweetalert2';
import Cliente from "@/models/Cliente.js";

const isEdit = ref(false);
const cliente = ref(new Cliente());
const route = useRoute();
const router = useRouter();

const fetchCliente = async () => {
  const id = route.params.id;
  console.log(id);
  if (id) {
    isEdit.value = true;
    try {
      const response = await clienteService.getClienteById(id);
      cliente.value = response; // Aqui você assume que a resposta já é um objeto cliente
      console.log(response);
    } catch (error) {
      Swal.fire({
        title: 'Erro ao buscar cliente',
        text: 'Houve um problema ao carregar os dados. Por favor, tente novamente mais tarde.',
        icon: 'error',
        confirmButtonText: 'OK',
        confirmButtonColor: '#d33',
      });
      console.error('Erro ao buscar cliente:', error);
    }
  }
};

const save = async () => {
  console.log(isEdit.value);

  try {
    if (isEdit.value) {

      await clienteService.updateCliente(cliente.value); // Aqui passa o cliente inteiro
      Swal.fire({
        icon: 'success',
        title: 'Cliente atualizado com sucesso!',
        showConfirmButton: false,
        timer: 1500
      });
    } else {
      // Criar novo cliente
      await clienteService.createCliente(cliente.value); // Aqui passa o cliente inteiro
      Swal.fire({
        icon: 'success',
        title: 'Cliente criado com sucesso!',
        showConfirmButton: false,
        timer: 1500
      });
    }
  } catch (error) {
    Swal.fire({
      icon: 'error',
      title: isEdit.value ? 'Erro ao atualizar cliente!' : 'Erro ao criar cliente!',
      text: error.message || 'Ocorreu um erro inesperado.',
    });
    console.error(error);
  }
};

const goBack = () => {
  router.push('/clientes');
};

onMounted(() => {
  fetchCliente();
});
</script>

<template>
  <div class="p-6 w-3/4 mx-auto">
    <h1 class="text-2xl font-bold text-gray-800 dark:text-white">{{ isEdit ? 'Editar Cliente' : 'Adicionar Cliente' }}</h1>
    <br />
    <form @submit.prevent="save" class="max-w mx-auto">
      <div class="mb-5">
        <label for="nome" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Nome</label>
        <input
            v-model="cliente.nome"
            type="text"
            id="nome"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            required
        />
      </div>

      <div class="mb-5">
        <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</label>
        <input
            v-model="cliente.email"
            type="email"
            id="email"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            required
        />
      </div>

      <div class="flex justify-between">
        <button
            type="submit"
            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        >
          {{ isEdit ? 'Atualizar' : 'Salvar' }}
        </button>

        <!-- Botão Voltar -->
        <button
            type="button"
            @click="goBack"
            class="text-white bg-gray-700 hover:bg-gray-800 focus:ring-4 focus:outline-none focus:ring-gray-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-gray-600 dark:hover:bg-gray-700 dark:focus:ring-gray-800"
        >
          Voltar
        </button>
      </div>
    </form>
  </div>
</template>
