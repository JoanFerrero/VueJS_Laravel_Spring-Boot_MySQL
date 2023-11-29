<template>
    <carrousel :data="state.categories" v-if="state.categories" @emitAction="redirectReservation"/>
    <h5 class="card-title">Mesas</h5>
    <div class="p-5">
        <div class="row row-cols-2 g-2">
            <div class="col" v-for="mesa in state.mesasInfinite">
                <div class="card bg-dark text-white">
                <img :src="mesa.photo" class="card-img" alt="Stony Beach"/>
                    <div class="card-img-overlay">
                        <h5 class="card-title">{{mesa.name_mesa}}</h5>
                        <h5 class="card-text">Capacidad: {{mesa.capacity}}</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <InfiniteLoading @infinite="scroll" :distance="1" />
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import CardCategory from '../../components/client/Card_Category.vue';
import { useRouter } from 'vue-router';
import carrousel from '../../components/client/carrousel.vue';
import { useMesaInfinite } from '../../composables/mesas/useMesa.js';
import InfiniteLoading from "v3-infinite-loading";

export default {
    components  : { InfiniteLoading, carrousel, CardCategory },
    setup() {
        const store = useStore();
        const router = useRouter();
        let page = 1;

        store.dispatch(`category/${Constant.INITIALIZE_CATEGORY}`);

        const state = reactive({
            categories: computed(() => store.getters['category/GetCategories']),
            mesasInfinite: useMesaInfinite(1, 2),
        });

        const redirectReservation = (item) => {
            const filters = {
                categories: [item.name_category],
                name_mesa: "",
            };
            const filters_ = btoa(JSON.stringify(filters));
            router.push({ name: "reservationFilters", params: { filters: filters_ } });
        }

        const addInfinite = (page) => {
            state.mesasInfinite = useMesaInfinite(page, 2);
        }

        const scroll = ($state) => {
            page++;
            if (page <= 3) {
                addInfinite(page);
            } else {
                $state.loaded();
            }
        }

        return { state, redirectReservation, addInfinite, scroll }
    }
}
</script>

<style>
.card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
}
</style>