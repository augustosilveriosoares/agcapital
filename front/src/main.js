import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/main.css';
import 'flowbite';
import 'flowbite-vue';

const app = createApp(App);

app.use(router);

app.mount('#app');
