<template>
  <div>
    <div class="mb-3">
      <label for="InputName" class="form-label">Nombre de Usuario</label><br>
      <label class="error" v-if="v$.username.$invalid">El usuario es obligatorio</label><br>
      <input type="text" class="form-control" id="InputName" v-model="state.profile.username">
    </div>
    <div class="mb-3">
      <label for="InputCapacity" class="form-label">Email</label><br>
      <label class="error" v-if="v$.email.$invalid">El correo es obligatorio</label><br>
      <input type="email" class="form-control" id="InputCapacity" v-model="state.profile.email">
    </div>
    <i class="bi bi-0-circle"></i>
    <button class="btn btn-primary" 
      @click="sendSubmit()" 
      :disabled="v$.username.$invalid || 
      v$.email.$invalid"
    >
      <p>Editar</p>
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
  components: {},
  props: {
    profile: Object
  },
  emits: {
    data: Object
  },
  setup(props) {
    const profile = props.profile;
    const { emit } = getCurrentInstance();
    const store = useStore();
    const toaster = createToaster({ position: "top-right" });

    const state = reactive({
      profile: { ...profile },
    });
    console.log(state.profile)

    const sendSubmit = () => {
      const data = {
        id: state.profile.id,
        email: state.profile.email,
        username: state.profile.username,
        password: state.profile.password,
        is_active: Boolean(state.profile.is_active),
        type: "client",
        photo: state.profile.photo
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
      }
    }));

    const v$ = useVuelidate(rules, state.profile);

    return {state, sendSubmit, v$}
  }
}

</script>

<style scoped>
</style>