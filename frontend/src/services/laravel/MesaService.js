import Api from "../api";
import secrets from "../../secrets";

export default {
    GetMesas() {
        return Api(secrets.URL_LARAVEL).get('mesa');
    },
    GetOneMesa(id) {
        return Api(secrets.URL_LARAVEL).get('mesa/' + id);
    },
    DeleteMesa(id) {
        return Api(secrets.URL_LARAVEL).delete(`mesa/${id}`);
    },
    CreateMesadata(data) {
        return Api(secrets.URL_LARAVEL).post('mesa', data);
    },
    UpdateMesa(data) {
        return Api(secrets.URL_LARAVEL).put(`mesa/${data.id}`, data);
    },
}