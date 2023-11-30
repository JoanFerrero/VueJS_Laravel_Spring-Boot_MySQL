<template>
  <div class="container" v-if="state.mesas">
    <h1 class="my-4"></h1>
    <div class="row">
      <div class="col-md-8">
        <img class="img-fluid image" :src="state.mesas.photo">
      </div>
      <div class="col-md-4">
        <h3 class="my-3">{{state.mesas.name_mesa}}</h3>
        <p>Capacidad: {{state.mesas.capacity}}</p>
        <h3 class="my-3">Horarios</h3>
        <ul>
          <li v-for="category in state.mesas.categories">{{category.name_category}}</li>
        </ul>
      </div>
    </div>
    <h3 class="my-4">Mesas Relacionadas</h3>
    <div class="row">
      <div class="col-md-3 col-sm-6 mb-4" v-for="mesa in state.mesasRelation" @click="showDetails(mesa.id)">
        <a href="#">
          <img class="img-fluid imageR" :src="mesa.photo">
        </a>
        <p>{{mesa.name_mesa}}</p>
      </div>
    </div>
  </div>
  <div v-else>
    <span>Esta mesa no existe.</span>
  </div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import { reactive, computed } from 'vue';
export default {
  setup(props) {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();

    const id = route.params.id;

    store.dispatch(`mesa/${Constant.INITIALIZE_ONE_STATE_MESA}`, id);
    store.dispatch(`mesa/${Constant.INITIALIZE_MESA_RELATION}`, id);

    const state = reactive({
      mesas: computed(() => store.getters["mesa/getOneMesa"]),
      mesasRelation: computed(() => store.getters["mesa/getMesasRelation"]),
    });
    
    const showDetails = (id) => {
      router.push({ name: "reservationDetails", params: { id } });
      setTimeout(() => {
        location.reload();
      }, 100);
    }

    return { state, showDetails }
  }
}
</script>

<style>
.image {
  width: 750px;
  height: 500px;
}

.imageR {
  width: 500px;
  height: 250px;
}
</style>