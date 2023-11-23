<template>
    <div class="p-5">
        <div class="row row-cols-3 g-3">
            <div class="col " v-for="category in state.categories">
                <CardCategory :key="category.id" :category="category" @emitAction="redirectReservation"/>
            </div>
        </div>
    </div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../Constant';
import CardCategory from '../components/client/Card_Category.vue';
import { useRouter } from 'vue-router';

export default {
    components  : { CardCategory },
    setup() {
        const store = useStore();
        const router = useRouter();

        store.dispatch(`category/${Constant.INITIALIZE_CATEGORY}`);

        const state = reactive({
            categories: computed(() => store.getters['category/GetCategories'])
        });

        const redirectReservation = (item) => {
            const filters = {
                categories: [item.name_category],
                name_mesa: "",
            };
            const filters_ = btoa(JSON.stringify(filters));
            router.push({ name: "reservationFilters", params: { filters: filters_ } });
        }

        return { state, redirectReservation }
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