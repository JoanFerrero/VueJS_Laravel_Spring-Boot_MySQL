<template>
    <div class="mt-3">
        <ul class="list list-inline">
            <li class="d-flex justify-content-between" v-for="mesa in state.mesas">
                <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                    <div class="ml-2 d-flex flex-column mr-2">
                        <h6 class="mb-0">Nombre: {{mesa.name_mesa}}</h6>
                        <h6 class="mb-0">Reservada: {{mesa.is_active === 1 ? 'SI' : 'NO'}}</h6>
                        <div class="d-flex flex-row mt-1 text-black-50 date-time">
                            <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">Capacitat: {{mesa.capacity}}</span></div>
                        </div>
                    </div>
                </div>
                <div class="d-flex flex-row align-items-center">
                    <div class="d-flex flex-column mr-2">
                        <div class="profile-image">
                            <button class="btn btn-primary" @click="updateMesa(mesa.id)">Editar</button>
                            <button class="btn btn-danger" @click="deleteMesa(mesa.id)">Borrar</button>
                        </div>
                    </div>
                    <i class="fa fa-ellipsis-h"></i>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import { ref, onMounted, reactive, computed } from 'vue';
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
        console.log(state)
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