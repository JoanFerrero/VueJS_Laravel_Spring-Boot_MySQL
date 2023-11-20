<template>
    <FormMesa :mesa="stateOne.mesa" @data="update_emit"/>
</template>

<script>
import Constant from '../../../Constant.js';
import { useRoute, useRouter } from 'vue-router'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { createToaster } from "@meforma/vue-toaster";
import FormMesa from '../../../components/dashboard/FormMesa.vue';
export default {
    components: { FormMesa },
    setup() {
        const toaster = createToaster({ position: "top-right" });
        const router = useRouter();
        const route = useRoute()
        const store = useStore();
        const id = route.params.id;

        store.dispatch(`mesaDashboard/${Constant.INITIALIZE_ONE_STATE_MESA}`, id);

        const stateOne = reactive({
            mesa: computed(() => store.getters["mesaDashboard/getOneMesa"])
        });

        const update_emit = (mesa) => {
            store.dispatch(`mesaDashboard/${Constant.UPDATE_ONE_MESA}`, mesa);
            toaster.success("Mesa updated")
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