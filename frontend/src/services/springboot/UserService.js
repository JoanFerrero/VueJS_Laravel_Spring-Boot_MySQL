import Api from "../api";
import secrets from "../../secrets";

export default {
    Register(data) {
        return Api(secrets.URL_SPRING).post('register', data);
    },
    Login(data) {
        return Api(secrets.URL_SPRING).post('login', data);
    },
    Logout() {
        return Api(secrets.URL_SPRING).post('logout');
    },
    Profile() {
        return Api(secrets.URL_SPRING).get('profile');
    },
    Login_Admin(data){
        return Api(secrets.URL_LARAVEL).post('login', data);
    },
    Logout_admin() {
        return Api(secrets.URL_LARAVEL).post('logout');
    },
}//export