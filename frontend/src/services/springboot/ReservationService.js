import Api from "../api";
import secrets from "../../secrets";

export default {
    GetReservation() {
        return Api(secrets.URL_SPRING).get('reservation');
    },
    PostReservation(data) {
        return Api(secrets.URL_SPRING).post(`reservation/${data.mesa_id}`, data);
    },
    PutReservation(data) {
        return Api(secrets.URL_SPRING).put(`reservation/${data.mesa_id}`, data);
    },
    DeleteReservation(id) {
        return Api(secrets.URL_SPRING).delete('reservation/' + id);
    },
}//export