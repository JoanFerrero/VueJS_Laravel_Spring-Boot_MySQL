<template>
  <div>
    <div class="mb-3">
      <label for="InputName" class="form-label">Nombre</label><br>
      <label class="error" v-if="v$.name_mesa.$invalid">This field is required or invalid</label><br>
      <input type="text" class="form-control" id="InputName" v-model="state.mesa.name_mesa">
    </div>
    <div class="mb-3">
      <label for="InputCapacity" class="form-label">Capacidad</label><br>
      <label class="error" v-if="v$.capacity.$invalid">This field is required or invalid</label><br>
      <input type="number" class="form-control" id="InputCapacity" v-model="state.mesa.capacity">
    </div>
    <div class="mb-3">
      <label for="InputCategory" class="form-label">Category</label><br>
      <v-select multiple v-model="state.mesa.categories" :options="state.categories"
          :getOptionLabel="categories => categories.name_category" />
    </div>
    <div class="mb-3">
      <label>Active</label><br>
      <input class="form-check-input" type="checkbox" value="" id="miCheckbox" v-model="state.mesa.is_active">
    </div>
    <div class="mb-3">
      <label for="InputPhoto" class="form-label">Photo</label><br>
      <label class="error" v-if="v$.photo.$invalid">This field is required and an url</label><br>
      <input type="url" v-model="state.mesa.photo" />
    </div>
    <i class="bi bi-0-circle"></i>
    <button class="btn btn-primary" 
      @click="sendSubmit()" 
      :disabled="v$.name_mesa.$invalid || 
      v$.capacity.$invalid || 
      v$.photo.$invalid"
    >
      <p v-if="isUpdate">Update</p>
      <p v-if="!isUpdate">Create</p>
    </button>
  </div>
</template>

<script>
import { reactive, getCurrentInstance, computed } from 'vue';
import Constant from '../../Constant';
import { useStore } from 'vuex';
import { useVuelidate } from '@vuelidate/core';
import { required, url, alphaNum, numeric, minValue } from '@vuelidate/validators';
import { createToaster } from "@meforma/vue-toaster";
export default {
  props: {
      mesa: Object
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
    const mesa = props.mesa;
    console.log(props)
    const { emit } = getCurrentInstance();
    const store = useStore();
    const toaster = createToaster({ position: "top-right" });

    store.dispatch(`categoryDashboard/${Constant.INITIALIZE_CATEGORY}`);

    const state = reactive({
        mesa: { ...mesa },
        categories: computed(() => store.getters['categoryDashboard/GetCategories'])
    });

    state.mesa.is_active = Boolean(state.mesa.is_active);
    const sendSubmit = () => {
      console.log(state.mesa.categories.length)
      const cat = state.mesa.categories;
      const names_cat = cat.map(item => item.name_category);
      state.mesa.categories = names_cat;
      if(state.mesa.categories.length > 0) {
        emit('data', state.mesa)
      } else {
        toaster.info('Necessitas seleccionar una categoria')
      }
    }

    const rules = computed(() => ({
        name_mesa: {
            required,
            alphaNum,
        },
        capacity: {
            required,
            numeric,
            minValue: minValue(1),
        },
        photo: {
            required,
            url,
        },
        is_active: {
            required,
        },
        categories: {
          required,
        }
    }));

    const v$ = useVuelidate(rules, state.mesa);
    return { state, sendSubmit, v$ }
  }
}
</script>

<style>
@import '../../../node_modules/vue-select/dist/vue-select.css';

</style>
