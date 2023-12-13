<template>
  <FormUser :isLogin="false" @send="register"/>
</template>

<script>
import FormUser from '../../components/client/FormUser.vue';
import Constant from '../../Constant';
import { useStore } from 'vuex';
export default {
  components: {FormUser},
  setup(props) {
    const store = useStore()
    const register = (user) => {
      store.dispatch(`user/${Constant.ADD_USER}`, user);
    }

    return { register }
  }
}
</script>