import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path:"", redirect:{ name:"home" } },
  { path:"/home", name:"home", component: () => import('../views/client/Home.vue') },
  { path:"/reservation", name:"reservation", component: () => import('../views/client/Reservation.vue') },
  { path: "/reservation/:filters", name: "reservationFilters", component: () => import('../views/client/Reservation.vue')},
  { path: "/reservation/details/:id", name: "reservationDetails", component: () => import('../views/client/Details.vue')},
  { path:"/dashboard", name:"dashboard", component: () => import('../views/dashboard/mesa/MesaList.vue') },
  { path:"/dashboard/mesa", name:"mesalist", component: () => import('../views/dashboard/mesa/MesaList.vue') },
  { path:"/dashboard/mesa/create", name:"mesacreate", component: () => import('../views/dashboard/mesa/MesaCreate.vue') },
  { path:"/dashboard/mesa/update/:id", name:"mesaupdate", component: () => import('../views/dashboard/mesa/MesaUpdate.vue') },
  { path:"/dashboard/category", name:"categorylist", component: () => import('../views/dashboard/category/CategoryList.vue') },
  { path:"/dashboard/category/create", name:"categorycreate", component: () => import('../views/dashboard/category/CategoryCreate.vue') },
  { path:"/dashboard/category/update/:id", name:"categoryupdate", component: () => import('../views/dashboard/category/CategoryUpdate.vue') },
];

const router = createRouter({
history: createWebHistory(),
routes
});

export default router;
