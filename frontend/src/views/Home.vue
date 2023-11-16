<template>
    <div>
        <h2>Home</h2>
    </div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../Constant';

export default {
    components  : {},
    setup() {
        const store = useStore();

        store.dispatch(`category/${Constant.INITIALIZE_CATEGORY}`);

        const state = reactive({
            categories: computed(() => store.getters['category/GetCategories'])
        });
        console.log(state)

        return { state }
    }
}
</script>

<style>

</style>