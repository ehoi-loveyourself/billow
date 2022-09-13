import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect: '/main',
  },

  {
    path: '/about',
    name: 'about',
    component: ()=>import("@/views/AboutView.vue"),
    redirect:'/home',
    children:[
      {
        path: '/situation',
        name: 'situation',
        component: () => import('@/views/SituationView.vue'),
      },
      {
        path: '/like',
        name: 'like',
        component: () => import('@/views/LikeView.vue'),
      },
    ]
  }

  
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
