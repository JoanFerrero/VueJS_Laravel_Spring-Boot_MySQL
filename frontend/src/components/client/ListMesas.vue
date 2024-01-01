<template>
  <h5 class="card-title">Mesas</h5>
  <div class="p-5">
    <div class="row row-cols-2 g-2">
      <div class="col" v-for="mesa in data">
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
  <InfiniteLoading @infinite="scroll" :distance="1">
    <template v-slot:no-more>
      <div class="no-more-message">
      <p>Todos los datos han sido cargados</p>
      </div>
    </template>
  </InfiniteLoading>
</template>

<script>
import { ref, getCurrentInstance } from 'vue';
import InfiniteLoading from "v3-infinite-loading";

export default {
  components: { InfiniteLoading },
  props: {
    data: Object
  },
  emits: {
    emitAction: Object
  },
  setup(props) {
    let page = ref(0);
    const { emit } = getCurrentInstance();

    const scroll = ($state) => {
      page.value++;
      if (page.value <= 4) {
        emit('emitAction', page.value);
      } else {
        $state.complete();
      }
    }

    return { scroll }
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