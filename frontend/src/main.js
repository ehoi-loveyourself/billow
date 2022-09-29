import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from "axios";
import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

const app = createApp(App);
app.config.globalProperties.$axios = axios;
app.use(router).use(BootstrapVue3).mount('#app');
window.Kakao.init('8cffc1914d92b7ff686e45c98b8469a4');