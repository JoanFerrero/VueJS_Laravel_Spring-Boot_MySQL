<script setup>
import { RouterView } from 'vue-router';
import HeaderDashboard from './views/Dashboard.vue';
import Header from './components/client/Header.vue';
import { useStore } from 'vuex';
import Constant from './Constant';
const store = useStore();
if (localStorage.getItem("token")) {
  store.dispatch(`user/${Constant.INITIALIZE_PROFILE}`);
}
</script>

<template>
  <Header />
  <HeaderDashboard />
</template>

<style scoped>

</style>
