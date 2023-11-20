<template>
    <FormCategory @data="create_emit"/>
</template>

<script>
import { useStore } from 'vuex';
import { createToaster } from "@meforma/vue-toaster";
import Constant from '../../../Constant';
import { useRouter } from 'vue-router';
import FormCategory from '../../../components/dashboard/FormCategory.vue';
export default {
    components: { FormCategory },
    setup() {
        const toaster = createToaster({ "position": "top-right", "duration": 1500 });
        const store = useStore();
        const router = useRouter();

        const create_emit = (category) => {
            console.log(category)
            store.dispatch(`categoryDashboard/${Constant.ADD_CATEGORY}`, category);
            toaster.success("Category created")
            redirects.return();
        }

        const redirects = {
            return: () => router.push({ name: 'categorylist' }),
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