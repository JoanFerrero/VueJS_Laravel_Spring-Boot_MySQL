<template>
  <input v-model="state.name_mesa" type="text" class="input" @input="sendName" @change="sendName()">
</template>

<script>
import { reactive, getCurrentInstance, computed } from 'vue';
export default {
  props: {
    filterName: String
  },
  emits: {
    emitSearch: String
  },
  setup(props) {
    const state = reactive({
      name_mesa: props.filterName ? props.filterName : ""
    });

    const { emit } = getCurrentInstance();

    const sendName = () => {
      emit('emitSearch', state.name_mesa);
    }

    return { state, sendName }
  }
}
</script>