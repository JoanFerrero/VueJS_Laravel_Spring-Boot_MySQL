<template>
    <Filters :filters="filters_URL" @onFilters="applyFilters" @onDelete="deleteAllFilters"/>
    <div v-if="state.mesas.length > 0">
        <div class="p-5">
            <div class="row row-cols-3 g-3">
                <div class="col" v-for="mesa in state.mesas">
                    <CardMesa :key="mesa.id" :mesa="mesa" />
                </div>
            </div>
        </div>
        <paginate
            v-if="state.totalPages > 1"
            class="d-flex justify-content-center"
            v-model="state.page"
            :page-count="state.totalPages"
            :page-range="3" 
            :margin-pages="1"
            :click-handler="clickCallback"
            :prev-text="'Prev'"
            :next-text="'Next'"
            :container-class="'pagination'"
            :page-class="'page-item'"
        >
        </paginate>
    </div>
    <div v-else>
        <span>No hay mesas</span>
    </div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import CardMesa from '../../components/client/Card_Mesa.vue';
import { useMesaFilters, useMesaPaginate } from '../../composables/mesas/useMesa.js';
import Paginate from "vuejs-paginate-next";
import Filters from '../../components/client/filters.vue';
export default {
  components  : { CardMesa, paginate: Paginate, Filters},
  setup() {
    const route = useRoute();
    const router = useRouter();

    let filters_URL = {
      categories: [],
      capacity: 0,
      name_mesa: "",
      order: 0,
      limit: 3,
      page: 1
    };

    try {
      if (route.params.filters !== '') {
        filters_URL = JSON.parse(atob(route.params.filters));
      }
    } catch (error) {}

    const state = reactive({
      mesas: useMesaFilters(filters_URL),
      page: filters_URL.page,
      totalPages: useMesaPaginate(filters_URL)
    });

    const applyFilters = (filters) => {
      const filtersNew = btoa(JSON.stringify(filters));
      router.push({ name: "reservationFilters", params: { filters: filtersNew } });
      state.page = filters.page;
      state.mesas = useMesaFilters(filters);
      state.totalPages = useMesaPaginate(filters);
    }

    const deleteAllFilters = (deleteFilters) => {
      router.push({ name: "reservation" });
      state.mesas = useMesaFilters(deleteFilters);
      state.page = 1;
      filters_URL = deleteFilters;
      state.totalPages = useMesaPaginate(deleteFilters);
    }

    const clickCallback = (pageNum) => {
      try {
        if (route.params.filters !== '') {
          filters_URL = JSON.parse(atob(route.params.filters));
        }
      } catch (error) {}
      filters_URL.page = pageNum;
      state.page = pageNum;
      applyFilters(filters_URL);
    }

    return { state, CardMesa, clickCallback, filters_URL, applyFilters, deleteAllFilters}
  }
}
</script>

<style>
@import "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css";

.card {
    margin: 0 auto;
    float: none;
    margin-bottom: 10px;
}
</style>