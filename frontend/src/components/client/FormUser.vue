<template>
    <div class="formlogin">
        <label class="labelform" for="username" v-if="!state.isLogin">Email</label>
        <input class="inputform" type="email" placeholder="Email" id="email" v-if="!state.isLogin" v-model="state.user.email">

        <label class="labelform" for="username">Username</label>
        <input class="inputform" type="text" placeholder="Username" id="username" v-model="state.user.username">

        <label class="labelform" for="password">Password</label>
        <input class="inputform" type="password" placeholder="Password" id="password" v-model="state.user.password">

        <button 
            class="button" 
            @click="sendRegister()" 
            v-if="!state.isLogin"
            :disabled="r$.email.$invalid ||
            r$.username.$invalid ||
            r$.password.$invalid"
        >
            Register
        </button>
        <button 
            class="button" 
            @click="sendLogin()" 
            v-if="state.isLogin"
            :disabled="l$.username.$invalid ||
            l$.password.$invalid"
        >
            Log In
        </button>
    </div>
</template>

<script>
import { reactive, computed, getCurrentInstance } from 'vue';
import { useVuelidate } from '@vuelidate/core';
import { required, url, alphaNum, numeric, minLength, email } from '@vuelidate/validators';
export default {
  components: {},
  props: {
    isLogin: Object
  },
  emits: {
    emitAction: Object
  },
  setup(props) {
    const { emit } = getCurrentInstance();

    const state = reactive({
        user: {
            username: '',
            email: '',
            password: '',
        },
        isLogin: props.isLogin
    })

    const sendRegister = () => {
        const data = {
            email: state.user.email,
            username: state.user.username,
            password: state.user.password
        }; 
        emit("send", data);
    }

    const sendLogin = () => {
        const data = {
            username: state.user.username,
            password: state.user.password
        }; 
        emit("send", data);
    }

    const rules_register = computed(() => ({
        email: {
            required,
            minLength: minLength(2),
            email
        },
        username: {
            required,
            alphaNum,
        },
        password: {
            required,
            minLength: minLength(4),
        }
    }));

    const rules_login = computed(() => ({
        username: {
            required,
            alphaNum,
        },
        password: {
            required,
            minLength: minLength(4),
        }
    }));

    const r$ = useVuelidate(rules_register, state.user);

    const l$ = useVuelidate(rules_login, state.user);

    return {state, sendRegister, sendLogin, r$, l$}
  }
}

</script>

<style>
      *,
*:before,
*:after{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
body{
    background-color: #080710;
}
.background{
    width: 430px;
    height: 520px;
    position: absolute;
    transform: translate(-50%,-50%);
    left: 50%;
    top: 50%;
}
.background .shape{
    height: 200px;
    width: 200px;
    position: absolute;
    border-radius: 50%;
}
.shape:first-child{
    background: linear-gradient(
        #1845ad,
        #23a2f6
    );
    left: -80px;
    top: -80px;
}
.shape:last-child{
    background: linear-gradient(
        to right,
        #ff512f,
        #f09819
    );
    right: -30px;
    bottom: -80px;
}
.formlogin{
    height: 520px;
    width: 400px;
    background-color: rgba(255,255,255,0.13);
    position: absolute;
    transform: translate(-50%,-50%);
    top: 50%;
    left: 50%;
    border-radius: 10px;
    backdrop-filter: blur(10px);
    border: 2px solid rgba(255,255,255,0.1);
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    padding: 50px 35px;
}
.formlogin *{
    font-family: 'Poppins',sans-serif;
    color: #ffffff;
    letter-spacing: 0.5px;
    outline: none;
    border: none;
}
.formlogin h3{
    font-size: 32px;
    font-weight: 500;
    line-height: 42px;
    text-align: center;
}

.labelform{
    display: block;
    margin-top: 30px;
    font-size: 16px;
    font-weight: 500;
}
.inputform{
    display: block;
    height: 50px;
    width: 100%;
    background-color: rgba(255,255,255,0.07);
    border-radius: 3px;
    padding: 0 10px;
    margin-top: 8px;
    font-size: 14px;
    font-weight: 300;
}
::placeholder{
    color: #e5e5e5;
}
.button{
    margin-top: 50px;
    width: 100%;
    background-color: #ffffff;
    color: #080710;
    padding: 15px 0;
    font-size: 18px;
    font-weight: 600;
    border-radius: 5px;
    cursor: pointer;
}
.social{
  margin-top: 30px;
  display: flex;
}
.social div{
  background: red;
  width: 150px;
  border-radius: 3px;
  padding: 5px 10px 10px 5px;
  background-color: rgba(255,255,255,0.27);
  color: #eaf0fb;
  text-align: center;
}
.social div:hover{
  background-color: rgba(255,255,255,0.47);
}
.social .fb{
  margin-left: 25px;
}
.social i{
  margin-right: 4px;
}
</style>