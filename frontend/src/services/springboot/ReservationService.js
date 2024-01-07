import Api from "../api";
import secrets from "../../secrets";

export default {
    GetReservation() {
        return Api(secrets.URL_SPRING).get('reservation');
    },
    PostReservation(data, id) {
        return Api(secrets.URL_SPRING).post('reservation/' + id, data);
    },
    DeleteReservation(id) {
        return Api(secrets.URL_SPRING).delete('reservation/' + id);
    },
}//export