<template>
    <FormMesa @data="create_emit"/>
</template>

<script>
import { useStore } from 'vuex';
import { createToaster } from "@meforma/vue-toaster";
import Constant from '../../../Constant';
import { useRouter } from 'vue-router';
import FormMesa from '../../../components/dashboard/FormMesa.vue';
export default {
    components: { FormMesa },
    setup() {
        const toaster = createToaster({ "position": "top-right", "duration": 1500 });
        const store = useStore();
        const router = useRouter();
        
        const create_emit = (mesa) => {
            console.log(mesa)
            store.dispatch(`mesaDashboard/${Constant.CREATE_ONE_MESA}`, mesa);
            toaster.success("Mesa created");
            redirects.return();
        }

        const redirects = {
            return: () => router.push({ name: 'mesalist' }),
        };

        return { create_emit, redirects }
    }
}
</script>

<style>
body {
  background: #eee;
}
</style>