<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Navbar from "@/components/Navbar.vue";
</script>

<template>
  <header>
    <Navbar></Navbar>
  </header>
  <div class="bg-slate-50 dark:bg-slate-600 h-screen">
    <RouterView />
  </div>


</template>
