<template>
    <FormUser @data="create_emit"/>
</template>

<script>
import { useStore } from 'vuex';
import { createToaster } from "@meforma/vue-toaster";
import Constant from '../../../Constant';
import { useRouter } from 'vue-router';
import FormUser from '../../../components/dashboard/FormUser.vue';
export default {
    components: { FormUser },
    setup() {
        const toaster = createToaster({ "position": "top-right", "duration": 1500 });
        const store = useStore();
        const router = useRouter();
        
        const create_emit = (user) => {
            store.dispatch(`userDashboard/${Constant.ADD_USER}`, user);
            toaster.success("User created");
            redirects.return();
        }

        const redirects = {
            return: () => router.push({ name: 'userlist' }),
        };

        return { create_emit }
    }
}
</script>

<style>
body {
  background: #eee;
}
</style>