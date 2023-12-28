import Api from "../api";
import secrets from "../../secrets";

export default {
    GetReservations() {
        return Api(secrets.URL_LARAVEL).get('reservation');
    },
    PutReservation(id) {
        return Api(secrets.URL_LARAVEL).put('reservation/' + id);
    },
    DeleteReservation(id) {
        return Api(secrets.URL_LARAVEL).delete('reservation/' + id);
    },
}