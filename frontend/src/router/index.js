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
  },
  {
    path: '/detail',
    name: 'detail',
    component: ()=> import('@/components/main/ProgramDetail.vue'),
  },
  {
    path: '/situationselect',
    name: 'situationselect',
    component: () => import('@/components/situation/SituationSelect.vue'),
  },
  
  {
    path: '/situationcollectdata',
    name: 'situationcollectdata',
    component: () => import('@/components/situation/SituationCollectData.vue'),
  },

  {
    path: '/situationresult',
    name: 'situationresult',
    component: () => import('@/components/situation/SituationResult.vue'),
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
