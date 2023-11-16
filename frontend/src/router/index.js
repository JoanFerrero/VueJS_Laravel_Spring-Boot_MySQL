import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path:"", redirect:{ name:"home" } },
  { path:"/home", name:"home", component: () => import('../views/Home.vue') },
  { path:"/reservation", name:"reservation", component: () => import('../views/Reservation.vue') },
  { path:"/dashboard", name:"dashboard", component: () => import('../views/Dashboard.vue') },
];

const router = createRouter({
history: createWebHistory(),
routes
});

export default router;
