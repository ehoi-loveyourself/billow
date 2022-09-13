import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AboutView from '../views/AboutView.vue';

const routes = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: HomeView,
  //   redirect: "/main",
  //   children: [
  //     {
  //       path:'/main',
  //       name:'about',
  //       component: AboutView,
  //       children:[
  //         {
  //           path: '/situation',
  //           name: 'situation',
  //           component: () => import('@/views/SituationView.vue'),
  //         },
  //         {
  //           path: '/like',
  //           name: 'like',
  //           component: () => import('@/views/LikeView.vue'),
  //         },
  //       ]
  //     },
  //   ],
  // },

  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect: "/loginmain",
    children: [
      {
        path:'/loginmain',
        name:'loginmain',
        component:()=>import("@/components/login/LoginMain.vue"),
      },
    ],
  },

  {
    path: '/about',
    name: 'about',
    component: AboutView,
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
              {
                path: '/main',
                name: 'main',
                component: () => import('@/views/MainView.vue'),
              },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
