<template>
    <FormUser :user="stateOne.user" @data="update_emit"/>
</template>

<script>
import Constant from '../../../Constant.js';
import { useRoute, useRouter } from 'vue-router'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { createToaster } from "@meforma/vue-toaster";
import FormUser from '../../../components/dashboard/FormUser.vue';
export default {
    components: { FormUser },
    setup() {
        const toaster = createToaster({ position: "top-right" });
        const router = useRouter();
        const route = useRoute()
        const store = useStore();
        const id = route.params.id;

        store.dispatch(`userDashboard/${Constant.INITIALIZE_ONE_USER}`, id);

        const stateOne = reactive({
            user: computed(() => store.getters["userDashboard/GetUser"])
        });

        const update_emit = (user) => {
            store.dispatch(`userDashboard/${Constant.UPDATE_USER}`, user);
            toaster.success("User updated")
            redirects.return();
        };

        const redirects = {
            return: () => router.push({ name: 'mesalist' }),
        };

        return { update_emit, redirects, stateOne }
    }
}
</script>


<style>
body {
  background: #eee;
}
</style>