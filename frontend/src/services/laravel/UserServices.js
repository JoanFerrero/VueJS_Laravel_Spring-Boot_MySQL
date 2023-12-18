import Api from "../api";
import secrets from "../../secrets";

export default {
    GetUsers() {
        return Api(secrets.URL_LARAVEL).get('user');
    },
    CreateUser(user) {
        return Api(secrets.URL_LARAVEL).post('user', user)
    },
    UpdateUser(data) {
        return Api(secrets.URL_LARAVEL).put(`user/${data.id}`, data)
    },
    DeleteUser(id) {
        return Api(secrets.URL_LARAVEL).delete(`user/${id}`)
    },
    GetOneUser(id) {
        return Api(secrets.URL_LARAVEL).get(`user/${id}`);
    },//GetOneUser
}