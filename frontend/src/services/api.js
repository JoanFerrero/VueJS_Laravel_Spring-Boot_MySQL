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

    return api
}