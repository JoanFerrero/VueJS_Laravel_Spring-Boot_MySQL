<template>
    <div>
        <p>{{stateOne.name_mesa}}</p>
    </div>
</template>

<script>
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import { reactive, computed } from 'vue';
export default {
    components  : {  },
    setup(props) {
        const store = useStore();
        const route = useRoute();

        const id = route.params.id;

        store.dispatch(`mesa/${Constant.INITIALIZE_ONE_STATE_MESA}`, id);

        const stateOne = reactive({
            mesa: computed(() => store.getters["mesa/getOneMesa"])
        });
        console.log(stateOne.mesa)

        return { stateOne }
    }
}
</script>

<style>
</style>