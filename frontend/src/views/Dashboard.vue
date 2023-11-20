<template>
    <div class="container-fluid" v-if="isAdmin">
        <div class="row flex-nowrap">
            <HeaderDashboard />
            <div class="col py-3">
                <RouterView />
            </div>
        </div>
    </div>
</template>
<script>
import { RouterView } from 'vue-router';
import HeaderDashboard from '../components/dashboard/HeaderDashboard.vue';
import { computed } from 'vue'

export default {
    components: { HeaderDashboard },
    computed: {
      isAdmin() {
        const path = this.$route.path.split('/');
        return path[1] == 'dashboard';
      },
  },
}
</script>
