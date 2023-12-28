<template>
    <table class="table table-warning">
      <thead>
        <tr>
          <th scope="col">#ID</th>
          <th scope="col">Date</th>
          <th scope="col">Time</th>
          <th scope="col">Username</th>
          <th scope="col">Table</th>
          <th scope="col">Activate</th>
          <th scope="col">Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="reservation in state.reservations">
          <th scope="row">{{reservation.id}}</th>
          <td>{{reservation.fecha_reserva}}</td>
          <td>{{reservation.hora}}</td>
          <td>{{reservation.user.username}}</td>
          <td>{{reservation.mesa.name_mesa}}</td>
          <td v-if="!reservation.accepted" >
            <button class="btn btn-primary" @click="updateReservation(reservation.id, true)">Active</button>
          </td>
          <td v-if="reservation.accepted">
            <button class="btn btn-primary" @click="updateReservation(reservation.id, false)">Desactivate</button>
          </td>
          <td>
            <button class="btn btn-danger" @click="deleteReservation(reservation.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '../../../Constant.js';
import { createToaster } from "@meforma/vue-toaster";
export default {
    components  : {},
    setup() {
      const store = useStore();
      const router = useRouter();
      const toaster = createToaster({ position: "top-right" });

      store.dispatch(`reservationDashboard/${Constant.INITIALIZE_RESERVATION}`);

      const updateReservation = (id, valor) => {
        const reservation = state.reservations.find(e => e.id === id);
        if (reservation) {
          store.dispatch(`reservationDashboard/${Constant.UPDATE_ONE_RESERVATION}`, reservation.id, valor);
        } else {
          toaster.info('You have to select at last ONE reservation');
        }
      }
      const deleteReservation = (id) => {
        const reservation = state.reservations.find(e => e.id === id);
        if (reservation) {
          store.dispatch(`reservationDashboard/${Constant.DELETE_ONE_RESERVATION}`, reservation.id);
        } else {
          toaster.info('You have to select at last ONE reservation');
        }
      }

      const state = reactive({
        reservations: computed(() => store.getters["reservationDashboard/getReservations"])
      })

      return { state, updateReservation, deleteReservation }
    }
}
</script>

<style>
body {
  background: #eee;
}

.icons i {
  color: #b5b3b3;
  border: 1px solid #b5b3b3;
  padding: 6px;
  margin-left: 4px;
  border-radius: 5px;
  cursor: pointer;
}

.activity-done {
  font-weight: 600;
}

.list-group li {
  margin-bottom: 12px;
}

.list-group-item {
}

.list li {
  list-style: none;
  padding: 10px;
  border: 1px solid #e3dada;
  margin-top: 12px;
  border-radius: 5px;
  background: #fff;
}

.checkicon {
  color: green;
  font-size: 19px;
}

.date-time {
  font-size: 12px;
}

.profile-image img {
  margin-left: 3px;
}
</style>