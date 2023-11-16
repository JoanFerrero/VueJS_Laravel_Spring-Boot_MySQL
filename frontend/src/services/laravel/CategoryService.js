import Api from "../api";
import secrets from "../../secrets";

export default {
    GetCategories() {
        return Api(secrets.URL_LARAVEL).get('category');
    },
    GetOneCategory(id) {
        return Api(secrets.URL_LARAVEL).get('category/' + id);
    },
    DeleteCategory(id) {
        return Api(secrets.URL_LARAVEL).delete(`category/${id}`);
    },
    CreateCategory(data) {
        return Api(secrets.URL_LARAVEL).post('category', data);
    },
    UpdateCategory(data) {
        return Api(secrets.URL_LARAVEL).put(`category/${data.id}`, data);
    },
}