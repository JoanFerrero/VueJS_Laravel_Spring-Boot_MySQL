<template>
  <carrousel :data="state.categories" v-if="state.categories" @emitAction="redirectReservation"/>
  <ListMesas :data="state.mesasInfinite" @emitAction="addInfinite"/>
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
import ListMesas from '../../components/client/ListMesas.vue';

export default {
  components: { InfiniteLoading, carrousel, CardCategory, ListMesas },
  setup() {
    const store = useStore();
    const router = useRouter();
    let page = 1;

    store.dispatch(`category/${Constant.INITIALIZE_CATEGORY}`);

    const state = reactive({
      categories: computed(() => store.getters['category/GetCategories']),
      mesasInfinite: useMesaInfinite(1, 4),
    });

    const redirectReservation = (item) => {
      const filters = {
        categories: [item.name_category],
        capacity: 0,
        name_mesa: "",
        order: 0,
        limit: 3,
        page: 1
      };
      const filters_ = btoa(JSON.stringify(filters));
      router.push({ name: "reservationFilters", params: { filters: filters_ } });
    }

    const addInfinite = (page) => {
      state.mesasInfinite = useMesaInfinite(page, 4)
    }

    return { state, redirectReservation, addInfinite }
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