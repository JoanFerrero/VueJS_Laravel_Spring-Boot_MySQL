<template>
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                <div class="card-body">
                    <div class="d-flex flex-column align-items-center text-center">
                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                    <div class="mt-3">
                        <h4>{{state.profile.username}}</h4>
                        <p class="text-secondary mb-1">{{state.profile.type}}</p>
                    </div>
                    </div>
                </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Nombre</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                {{state.profile.username}}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Correo</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                {{state.profile.email}}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Tipo</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                {{state.profile.type}}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Contaseña</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ********
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <h1>Reservation</h1>
    <ul class="list-group">
        <li class="list-group-item" v-for="reservation in state.reservations">{{reservation.categoria}} {{reservation.accepted}} {{reservation.fecha_reserva}}</li>
    </ul>
</div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import { useReservationList } from '../../composables/reservation/useReservation.js';
export default {
  setup(props) {
    const store = useStore();
    const state = reactive({
        profile: computed(() => store.getters['user/GetProfile']),
        reservations: useReservationList()
    });
console.log(state)
    return {state}
  }
}
</script>

<style>
.main-body {
    padding: 15px;
}
.card {
    box-shadow: 0 1px 3px 0 rgba(0,0,0,.1), 0 1px 2px 0 rgba(0,0,0,.06);
}

.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0 solid rgba(0,0,0,.125);
    border-radius: .25rem;
}

.card-body {
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1rem;
}

.gutters-sm {
    margin-right: -8px;
    margin-left: -8px;
}

.gutters-sm>.col, .gutters-sm>[class*=col-] {
    padding-right: 8px;
    padding-left: 8px;
}
.mb-3, .my-3 {
    margin-bottom: 1rem!important;
}

.bg-gray-300 {
    background-color: #e2e8f0;
}
.h-100 {
    height: 100%!important;
}
.shadow-none {
    box-shadow: none!important;
}

</style>