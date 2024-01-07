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
                <!--<div v-if="state.edit === true">
                    <FormEditProfile :profile="state.profile" @data="update_emit"/>
                    <br />
                    <button class="p-2 py-2 px-2" @click="editProfile()">Cancelar</button>
                </div>-->
                <div class="card mb-3" v-if="state.edit === false">
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
                        <hr>
                        <!--<div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Editar Perfil</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <button class="p-2 py-2 px-2" @click="editProfile()">Editar Perfil</button>
                            </div>
                        </div>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <h1>Reservas</h1>
    <ol class="list-group list-group-numbered">
        <li class="list-group-item d-flex justify-content-between align-items-start" v-for="reservation in state.reservations">
            <div class="ms-2 me-auto">
            <div class="fw-bold">{{reservation.fecha_reserva}}</div>
            {{reservation.categoria}}
            </div>
            <span class="badge bg-success rounded-pill" v-if="reservation.accepted === true">Aceptado</span>
            <span class="badge bg-danger rounded-pill" v-if="reservation.accepted === false">Esperando confirmacion...</span>
            <button class="p-2 py-2 px-2" v-if="!reservation.accepted" @click="cancellReservation(reservation.id)">Cancelar Reserva</button>
        </li>
    </ol>
</div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import { useReservationList, useReservationDelete } from '../../composables/reservation/useReservation.js';
import FormEditProfile from '../../components/client/FormEditProfile.vue';
import { useRoute, useRouter } from 'vue-router'
export default {
  components: {FormEditProfile},
  setup(props) {
    const router = useRouter();
    const store = useStore();
    const state = reactive({
        profile: computed(() => store.getters['user/GetProfile']),
        reservations: useReservationList(),
        edit: false,
    });

    const cancellReservation = (id) => {
        state.reservations = useReservationDelete(id);
    }

    //const editProfile = () => {
    //    state.edit = !state.edit;
    //}

    //const update_emit = (user) => {
      //store.dispatch(`userDashboard/${Constant.UPDATE_USER}`, user);
      //toaster.success("User updated")
      //state.profile.username = user.username;
      //state.profile.email = user.email;
      //state.edit = !state.edit;
    //};

    return {state, cancellReservation}
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