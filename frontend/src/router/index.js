import { createRouter, createWebHistory } from 'vue-router';
import AuthGuards from '../services/guards/AuthGuards';

const routes = [
  { path: "", redirect:{ name:"home" } },
  { path: "/home", name:"home", component: () => import('../views/client/Home.vue') },
  { path: "/reservation", name:"reservation", component: () => import('../views/client/Reservation.vue') },
  { path: "/reservation/:filters", name: "reservationFilters", component: () => import('../views/client/Reservation.vue')},
  { path: "/reservation/details/:id", name: "reservationDetails", component: () => import('../views/client/Details.vue')},
  { 
    path: "/register", 
    name: "register", 
    component: () => import('../views/client/Register.vue'),
    beforeEnter: AuthGuards.noAuthGuard, meta: { requiresAuth: true }
  },
  { 
    path: "/login", 
    name: "login", 
    component: () => import('../views/client/Login.vue'),
    beforeEnter: AuthGuards.noAuthGuard, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard", 
    name:"dashboard", 
    component: () => import('../views/dashboard/mesa/MesaList.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard/mesa", 
    name:"mesalist", 
    component: () => import('../views/dashboard/mesa/MesaList.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard/mesa/create", 
    name:"mesacreate", 
    component: () => import('../views/dashboard/mesa/MesaCreate.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard/mesa/update/:id", 
    name:"mesaupdate", 
    component: () => import('../views/dashboard/mesa/MesaUpdate.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard/category", 
    name:"categorylist", 
    component: () => import('../views/dashboard/category/CategoryList.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true } 
  },
  { 
    path: "/dashboard/category/create", 
    name:"categorycreate", 
    component: () => import('../views/dashboard/category/CategoryCreate.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard/category/update/:id", 
    name:"categoryupdate", 
    component: () => import('../views/dashboard/category/CategoryUpdate.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard/user", 
    name:"userlist", 
    component: () => import('../views/dashboard/user/UserList.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard/user/create", 
    name:"usercreate", 
    component: () => import('../views/dashboard/user/UserCreate.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  { 
    path: "/dashboard/user/update/:id", 
    name:"userupdate", 
    component: () => import('../views/dashboard/user/UserUpdate.vue'),
    beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true }
  },
  {
    path: "/profile",
    name: "userprofile",
    component: () => import('../views/client/Profile.vue'),
    beforeEnter: AuthGuards.AuthGuard, meta: { requiresAuth: true }
  }
];

const router = createRouter({
history: createWebHistory(),
routes
});

export default router;
