import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('../views/AtividadesView.vue'),
    },
    {
      path: '/projetos',
      component: () => import('../views/ProjetosView.vue'),
    },

    {
      path: '/clientes',
      component: () => import('../views/ClientesView.vue'),
    },
    {
      path: '/clientes/edit/:id', // Adicionando o parâmetro `id`
      name: 'EditarCliente',
      component: () => import('../views/ClientesForm.vue'),
      props: true,

    },
    {
      path: '/clientes/edit', // Adicionando o parâmetro `id`
      name: 'NovoCliente',
      component: () => import('../views/ClientesForm.vue'),
    },

    {
      path: '/projetos/edit', // Adicionando o parâmetro `id`
      name: 'NovoProjeto',
      component: () => import('../views/ProjetosForm.vue'),
    },
    {
      path: '/projetos/edit/:id', // Adicionando o parâmetro `id`
      name: 'EditarProjeto',
      component: () => import('../views/ProjetosForm.vue'),
    },

  ],
});

export default router;
