import Api from "../api";
import secrets from "../../secrets";

export default {
    GetReservations() {
        return Api(secrets.URL_LARAVEL).get('reservation');
    },
    PutReservation(id, data) {
        return Api(secrets.URL_LARAVEL).get('reservation/' + id, data);
    },
    DeleteReservation(id) {
        return Api(secrets.URL_LARAVEL).delete('reservation/' + id);
    },
}