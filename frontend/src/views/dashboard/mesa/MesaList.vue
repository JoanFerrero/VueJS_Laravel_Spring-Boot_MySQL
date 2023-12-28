<template>
    <table class="table table-warning">
      <thead>
        <tr>
          <th scope="col">#ID</th>
          <th scope="col">Name</th>
          <th scope="col">Reserved</th>
          <th scope="col">Capacity</th>
          <th scope="col">Edit</th>
          <th scope="col">Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="mesa in state.mesas">
          <th scope="row">{{mesa.id}}</th>
          <td>{{mesa.name_mesa}}</td>
          <td>{{mesa.is_active === 1 ? 'Activa' : 'No Activa' }}</td>
          <td>{{mesa.capacity}}</td>
          <td>
            <button class="btn btn-primary" @click="updateMesa(mesa.id)">Edit</button>
          </td>
          <td>
            <button class="btn btn-danger" @click="deleteMesa(mesa.id)">Delete</button>
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

        store.dispatch(`mesaDashboard/${Constant.INITIALIZE_MESA}`);

        const updateMesa = (id) => {
          const mesa = state.mesas.find(e => e.id === id);
          if (mesa) {
            router.push({ name: 'mesaupdate', params: { id } });
          } else {
            toaster.info('You have to select ONE mesa');
          }
        }
        const deleteMesa = (id) => {
          const mesa = state.mesas.find(e => e.id === id);
          if (mesa) {
            store.dispatch(`mesaDashboard/${Constant.DELETE_ONE_MESA}`, mesa.id);
          } else {
            toaster.info('You have to select at last ONE mesa');
          }
        }

        const state = reactive({
            mesas: computed(() => store.getters["mesaDashboard/getMesas"])
        })

        return { state, updateMesa, deleteMesa }
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