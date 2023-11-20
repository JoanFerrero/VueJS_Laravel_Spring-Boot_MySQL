<template>
    <div v-for="mesa in state.mesas">
        <h2>{{mesa.name_mesa}}</h2>
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

        store.dispatch(`mesa/${Constant.INITIALIZE_MESA}`)

        const state = reactive({
            mesas: computed(() => store.getters["mesa/getMesas"])
        })

        return { state }
    }
}
</script>

<style>

</style>