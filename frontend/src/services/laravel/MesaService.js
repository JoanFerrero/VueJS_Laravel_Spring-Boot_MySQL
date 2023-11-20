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
        return Api(secrets.URL_LARAVEL).delete(`mesa/${id}`)
    },
    UpdateOneMesa(mesa) {
        return Api(secrets.URL_LARAVEL).put(`mesa/${mesa.id}`, mesa)
    },
    CreateOneMesa(mesa) {
        return Api(secrets.URL_LARAVEL).post(`mesa`, mesa)
    },
}