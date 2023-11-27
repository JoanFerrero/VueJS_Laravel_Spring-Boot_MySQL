<template>
    <div class="p-5">
        <div class="row row-cols-3 g-3">
            <div class="col " v-for="mesa in state.mesas">
                <CardMesa :key="mesa.id" :mesa="mesa" />
            </div>
        </div>
    </div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import CardMesa from '../../components/client/Card_Mesa.vue';
import { useMesaFilters } from '../../composables/mesas/useMesa.js';

export default {
    components  : { CardMesa },
    setup() {
        const route = useRoute();

        let filters_URL = {
            categories: [],
            name_mesa: ""
        };

        try {
            if (route.params.filters !== '') {
                filters_URL = JSON.parse(atob(route.params.filters));
            }
        } catch (error) {}

        const state = reactive({
            mesas: useMesaFilters(filters_URL)
        });

        return { state, CardMesa }
    }
}
</script>

<style>
.card {
        margin: 0 auto;
        float: none;
        margin-bottom: 10px;
}
</style>