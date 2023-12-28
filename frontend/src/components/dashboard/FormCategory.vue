<template>
  <div>
    <div class="mb-3">
      <label for="InputName" class="form-label">Nombre</label><br>
      <label class="error" v-if="v$.name_category.$invalid">This field is requiredl</label><br>
      <input type="text" class="form-control" v-model="state.category.name_category">
    </div>

    <div class="mb-3">
      <label for="InputPhoto" class="form-label">Photo</label><br>
      <label class="error" v-if="v$.photo.$invalid">This field is required</label><br>
      <input type="url" v-model="state.category.photo" />
    </div>

    <button class="btn btn-primary" 
      @click="sendSubmit()" 
      :disabled="v$.name_category.$invalid || 
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
export default {
  props: {
    category: Object
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
    const category = props.category;
    const { emit } = getCurrentInstance();
    const store = useStore();

    const state = reactive({
      category: { ...category }
    });

    const sendSubmit = () => {
      emit('data', state.category)
    }

    const rules = computed(() => ({
        name_category: {
          required,
        },
        photo: {
          required,
          url,
        }
    }));

    const v$ = useVuelidate(rules, state.category);
    return { state, sendSubmit, v$ }
  }
}
</script>
