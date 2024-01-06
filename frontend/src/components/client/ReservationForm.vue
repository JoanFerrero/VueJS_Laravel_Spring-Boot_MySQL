<template>
  <div>
    <h3 class="my-3">Horarios disponibles para reservar</h3>
    <select class="form-select" aria-label="Default select example" v-model="state.reserva.category">
      <option v-for="category in reservation">{{category.name_category}}</option>
    </select>
    <br>
    <button type="button" class="btn btn-secondary btn-lg" @click="sendReserva()">Reservar</button>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { reactive, computed } from 'vue';
import { useReservationCreate } from '../../composables/reservation/useReservation.js';
import { useRouter } from 'vue-router';
import { createToaster } from "@meforma/vue-toaster";
export default {
  props: {
    mesa: Object,
    reservation: Object,
  },
  emits: {
    emitAction: Object
  },
  setup(props) {
    const store = useStore();
    const router = useRouter();
    const toaster = createToaster({ "position": "top-right", "duration": 1500 });

    const state = reactive({
      isLogged: computed(() => store.getters['user/GetIsAuth']),
      reserva: {
        category: 'Selecciona una opcion valida',
      }
    });

    const sendReserva = () => {
      if(state.isLogged) {
        if(state.reserva.category != 'Selecciona una opcion valida'){
          const hoy = new Date();
          const data = {
            categoria: state.reserva.category,
            fecha_reserva: hoy.getDate() + '-' + hoy.getMonth() + '-' + hoy.getFullYear(),
            accepted: false
          }
          useReservationCreate(data, props.mesa.id);
          toaster.success(`Reserva a la hora ${state.reserva.category}`);
          router.push({ name: "reservation" });
        }
      } else {
        toaster.info('Necessitas estar logeado para realizar una reserva');
      }
    }

    return { state, sendReserva }
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