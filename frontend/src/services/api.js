import axios from 'axios';
import secrets from '../secrets';
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { createToaster } from "@meforma/vue-toaster";
import Constant from '../Constant.js';


export default (URL) => {
    const toaster = createToaster({ "position": "top-right", "duration": 1500 });
    const store = useStore();
    const router = useRouter();

    const api = axios.create({
        baseURL: URL
    })

    const token = URL === secrets.URL_LARAVEL ? localStorage.getItem('token_admin') : localStorage.getItem('token');
    if (token) {
        api.defaults.headers.common.Authorization = `Bearer ${token}`;
    }

    api.interceptors.response.use(
        (response) => response,
        (error) => {
            console.log(api.defaults.headers.common.Authorization)
            if (error.response.status === 401) {
                toaster.error('Error');
                router.push({ name: "home" });
            }
            return Promise.reject(error);
        }
    );

    return api
}