<template>
  <div>
    <div class="mb-3">
      <label for="InputName" class="form-label">Username</label><br>
      <label class="error" v-if="v$.username.$invalid">This field is required or invalid</label><br>
      <input type="text" class="form-control" id="InputName" v-model="state.user.username">
    </div>
    <div class="mb-3">
      <label for="InputCapacity" class="form-label">Email</label><br>
      <label class="error" v-if="v$.email.$invalid">This field is required or invalid</label><br>
      <input type="email" class="form-control" id="InputCapacity" v-model="state.user.email">
    </div>
    <div class="mb-3">
      <label for="InputCapacity" class="form-label" v-if="!isUpdate">Password</label><br>
      <label class="error" v-if="v$.password.$invalid">This field is required or invalid</label><br>
      <input type="password" class="form-control" id="InputCapacity" v-model="state.user.password" v-if="!isUpdate">
    </div>
    <div class="mb-3">
      <label>Active</label><br>
      <input class="form-check-input" type="checkbox" value="" id="miCheckbox" v-model="state.user.is_active">
    </div>
    <div class="mb-3">
      <label for="InputPhoto" class="form-label">Photo</label><br>
      <label class="error" v-if="v$.photo.$invalid">This field is required and an url</label><br>
      <input type="url" v-model="state.user.photo" />
    </div>
    <i class="bi bi-0-circle"></i>
    <button class="btn btn-primary" 
      @click="sendSubmit()" 
      :disabled="v$.username.$invalid || 
      v$.email.$invalid || 
      v$.photo.$invalid ||
      v$.password.$invalid"
    >
      <p v-if="!isUpdate">Create</p>
    </button>
  </div>
</template>

<script>
import { reactive, getCurrentInstance, computed } from 'vue';
import Constant from '../../Constant';
import { useStore } from 'vuex';
import { useVuelidate } from '@vuelidate/core';
import { required, url, alphaNum, numeric, minValue, minLength } from '@vuelidate/validators';
import { createToaster } from "@meforma/vue-toaster";
export default {
  props: {
    user: Object
  },
  emits: {
    data: Object
  },
  computed: {
    isUpdate() {
      const path = this.$route.path.split('/');
      return path[3] == 'update';
    },
  },
  setup(props) {
    const user = props.user;
    const { emit } = getCurrentInstance();
    const store = useStore();
    const toaster = createToaster({ position: "top-right" });

    const state = reactive({
      user: { ...user },
    });

    const sendSubmit = () => {
      if(state.user.photo === '') {
        state.user.photo = 'https://img.freepik.com/vector-premium/icono-perfil-usuario-estilo-plano-ilustracion-vector-avatar-miembro-sobre-fondo-aislado-concepto-negocio-signo-permiso-humano_157943-15752.jpg';
      }
      const data = {
        email: state.user.email,
        username: state.user.username,
        password: state.user.password,
        is_active: Boolean(state.user.is_active),
        type: "client",
        photo: state.user.photo
      }; 
      emit('data', data);
    }

    const rules = computed(() => ({
      username: {
          required,
          alphaNum,
      },
      email: {
          required,
      },
      password: {
        required,
        minLength: minLength(4),
      },
      photo: {
        url,
      },
    }));

    const v$ = useVuelidate(rules, state.user);
    return { state, sendSubmit, v$ }
  }
}
</script>

<style>
@import '../../../node_modules/vue-select/dist/vue-select.css';

</style>
