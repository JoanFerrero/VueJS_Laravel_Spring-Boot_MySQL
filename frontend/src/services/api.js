import axios from 'axios';
import secrets from '../secrets';


export default (URL) => {

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
            console.error(error);
            if (error.response.status === 401) {
                toaster.error('Forced logout. Unauthorized action');
                store.dispatch(`user/${Constant.LOGOUT}`);
                router.push({ name: "home" });
            }
            return Promise.reject(error);
        }//end if
    );//response interceptor remove token and user when unauthorized

    return api
}