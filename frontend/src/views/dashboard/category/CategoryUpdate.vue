<template>
    <FormCategory :category="stateOne.category" @data="update_emit"/>
</template>

<script>
import Constant from '../../../Constant.js';
import { useRoute, useRouter } from 'vue-router'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { createToaster } from "@meforma/vue-toaster";
import FormCategory from '../../../components/dashboard/FormCategory.vue';
export default {
    components: { FormCategory },
    setup() {
        const toaster = createToaster({ position: "top-right" });
        const router = useRouter();
        const route = useRoute()
        const store = useStore();
        const id = route.params.id;

        store.dispatch(`categoryDashboard/${Constant.INITIALIZE_ONE_CATEGORY}`, id);

        const stateOne = reactive({
            category: computed(() => store.getters["categoryDashboard/GetCategory"])
        });

        const create_emit = (category) => {
            store.dispatch(`categoryDashboard/${Constant.UPDATE_CATEGORY}`, data_dispatch);
            toaster.success('Category updated');
            redirects.return();
        }

        const redirects = {
            return: () => router.push({ category: 'categorylist' }),
        };

        return { create_emit, redirects, stateOne }
    }
}
</script>

<style>
body {
  background: #eee;
}
</style>