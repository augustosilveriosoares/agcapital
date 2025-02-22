<script setup>
import { ref, computed, watchEffect } from 'vue';
import { FwbPagination, FwbButtonGroup, FwbButton } from 'flowbite-vue';
import { PencilIcon, TrashIcon } from '@heroicons/vue/24/solid';
import projetoService from '@/services/projetoService';
import Swal from 'sweetalert2';
import { useRouter } from 'vue-router';

const router = useRouter();

const paginaAtual = ref(1);
const itensPorPagina = ref(10);
const projetos = ref([]);
const totalProjetos = ref(0);

const fetchProjetos = async () => {
  try {
    const response = await projetoService.getProjetos(paginaAtual.value - 1, itensPorPagina.value);
    projetos.value = response.data.content;
    totalProjetos.value = response.data.totalElements;
  } catch (error) {
    Swal.fire({
      title: 'Erro ao buscar projetos',
      text: 'Houve um problema ao carregar os dados. Por favor, tente novamente mais tarde.',
      icon: 'error',
      confirmButtonText: 'OK',
      confirmButtonColor: '#d33',
    });
    console.error('Erro ao buscar projetos:', error);
  }
};

watchEffect(() => {
  fetchProjetos();
});

const editarProjeto = (projeto) => {
  console.log(`Editar projeto com ID: ${projeto.id}`);
  router.push({ path: '/projetos/edit', query: { projeto: JSON.stringify(projeto) } });
};

const excluirProjeto = async (projetoId) => {
  try {
    await projetoService.deleteProjeto(projetoId);
    Swal.fire({
      title: 'Sucesso!',
      text: 'Projeto excluído com sucesso.',
      icon: 'success',
    });
    fetchProjetos(); // Recarregar a lista após exclusão
  } catch (error) {
    Swal.fire({
      title: 'Atenção',
      text: error.response?.data?.message || 'Erro ao excluir projeto',
      icon: 'warning',
      confirmButtonText: 'OK',
      confirmButtonColor: '#d33',
    });
  }
};
</script>

<template>
  <div class="p-6 w-3/4 mx-auto">
    <div class="flex justify-between items-center mb-4">
      <h1 class="text-2xl font-bold text-gray-800 dark:text-white">Projetos</h1>
      <fwb-button color="blue" @click="router.push({ name: 'NovoProjeto' })">Adicionar</fwb-button>
    </div>

    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
      <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" class="px-6 py-3">Nome</th>
            <th scope="col" class="px-6 py-3 text-right">Status</th>
            <th scope="col" class="px-6 py-3 text-right">Total de Atividades</th>
            <th scope="col" class="px-6 py-3 text-right">Ação</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="projeto in projetos"
            :key="projeto.id"
            class="odd:bg-white even:bg-gray-50 dark:odd:bg-gray-900 dark:even:bg-gray-800 border-b dark:border-gray-700"
          >
            <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
              {{ projeto.nome }}
            </td>
            <td class="px-6 py-4 text-right">
              {{ projeto.status.descricao }}
            </td>
            <td class="px-6 py-4 text-right">
              {{ projeto.atividades.length }}
            </td>
            <td class="px-6 py-4 text-right">
              <fwb-button-group class="space-x-2">
                <fwb-button @click="editarProjeto(projeto)" color="default">
                  <PencilIcon class="w-4 h-4 text-white" />
                </fwb-button>
                <fwb-button @click="excluirProjeto(projeto.id)" color="red">
                  <TrashIcon class="w-4 h-4 text-white" />
                </fwb-button>
              </fwb-button-group>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="flex justify-center mt-4">
      <FwbPagination
        v-model="paginaAtual"
        :total-items="totalProjetos"
        :items-per-page="itensPorPagina"
        previous-label="<"
        next-label=">"
      />
    </div>
  </div>
</template>

<style scoped>
@media (max-width: 640px) {
  .flex.justify-between {
    flex-direction: column;
    align-items: flex-start;
  }
  .flex.justify-between .fwb-button {
    margin-top: 10px;
  }
}
</style>
