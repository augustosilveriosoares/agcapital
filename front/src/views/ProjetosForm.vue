<script>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import projetoService from '@/services/projetoService';
import statusService from "@/services/statusService.js";
import Swal from 'sweetalert2';
import Status from '../models/Status.js'
import Projeto from '../models/Projeto.js'
import Cliente from '../models/Cliente.js'

const route = useRoute();
const router = useRouter();
const isEdit = ref(false)
const projeto = ref(new Projeto('', '', new Status(''), new Cliente()));
const statusList = ref([])

const fetchProjeto= async () => {
  const id = route.params.id;
  if (id) {
    isEdit.value = true;
    try {
      const response = await projetoService.getClienteById(id);
      projeto.value = response;
      console.log(response);
    } catch (error) {
      Swal.fire({
        title: 'Erro ao buscar Projeto',
        text: 'Houve um problema ao carregar os dados. Por favor, tente novamente mais tarde.',
        icon: 'error',
        confirmButtonText: 'OK',
        confirmButtonColor: '#d33',
      });
      console.error('Erro ao buscar cliente:', error);
    }
  }
  try {
    const response = await statusService.getStatus();
    statusList.value = response;
    console.log('statusList', response);

  } catch (error) {
    Swal.fire({
      title: 'Erro ao buscar Status',
      text: 'Houve um problema ao carregar os dados. Por favor, tente novamente mais tarde.',
      icon: 'error',
      confirmButtonText: 'OK',
      confirmButtonColor: '#d33',
    });
    console.error('Erro ao buscar status:', error);
  }

  onMounted(() => {
    fetchProjeto();
  });

};


</script>

<template>
  <div class="p-6 w-3/4 mx-auto">
    <h1 class="text-2xl font-bold text-gray-800 dark:text-white">
      {{ isEdit ? 'Editar Projeto' : 'Adicionar Projeto' }}
    </h1>
    <br />
    <form @submit.prevent="handleSubmit" class="max-w mx-auto">
      <!-- Campo Nome -->
      <div class="mb-5">
        <label for="nome" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Nome</label>
        <input
          v-model="projeto.nome"
          type="text"
          id="nome"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
        />
        <p v-if="errors.nome" class="text-red-500 text-sm mt-1">{{ errors.nome }}</p>
      </div>

      <!-- Campo Status
      <div class="mb-5">
        <label for="status" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Status</label>
        <select
          v-model="projeto.status"
          id="status"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"

        >
          <option v-for="(status, key) in statusEnum" :key="key" :value="key">
            {{ status }}
          </option>
        </select>
        <p v-if="errors.status" class="text-red-500 text-sm mt-1">{{ errors.status }}</p>
      </div> -->
       <!-- Campo Cliente -->
      <div class="mb-5">
        <label for="cliente" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Cliente</label>
        <input
          v-model="projeto.cliente.nome"
          type="text"
          id="nome"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
        />
        <p v-if="errors.nome" class="text-red-500 text-sm mt-1">{{ errors.nome }}</p>
      </div>

      <!-- Botões -->
      <div class="flex justify-between">
        <button
          type="submit"
          class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        >
          {{ isEdit ? 'Atualizar' : 'Salvar' }}
        </button>

        <button
          type="button"
          @click="goBack"
          class="text-white bg-gray-800 hover:bg-gray-900 focus:ring-4 focus:outline-none focus:ring-gray-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center  dark:focus:ring-gray-800"
        >
          Voltar
        </button>
      </div>
    </form>
  </div>
</template>

