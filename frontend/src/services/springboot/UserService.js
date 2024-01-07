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
    //UpdateProfile() {
    //    return Api(secrets.URL_SPRING).put('')
    //},
    Login_Admin(data){
        return Api(secrets.URL_LARAVEL).post('login', data);
    },
    Logout_Admin() {
        return Api(secrets.URL_LARAVEL).post('logout');
    },
    isAdmin() {
        return Api(secrets.URL_LARAVEL).get('isAdmin');
    },//Profile
}//export