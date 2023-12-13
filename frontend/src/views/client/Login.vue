<template>
  <FormUser :isLogin="true" @send="login"/>
</template>

<script>
import FormUser from '../../components/client/FormUser.vue';
export default {
  components: {FormUser},
  setup(props) {
    
    const login = (user) => {
      console.log(user);
    }
    
    return { login }
  }
}
</script>