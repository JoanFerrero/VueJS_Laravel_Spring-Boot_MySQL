<template>
  <FormUser :isLogin="true" @send="login"/>
</template>

<script>
import FormUser from '../../components/client/FormUser.vue';
import Constant from '../../Constant';
import { useStore } from 'vuex';
export default {
  components: {FormUser},
  setup(props) {
    const store = useStore()
    const login = (user) => {
      store.dispatch(`user/${Constant.LOGIN}`, user);
    }
    
    return { login }
  }
}
</script>