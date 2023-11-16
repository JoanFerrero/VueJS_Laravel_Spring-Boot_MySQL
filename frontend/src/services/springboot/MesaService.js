import Api from "../api";
import secrets from "../../secrets";

export default {

    GetMesas() {
        return Api(secrets.URL_SPRING).get('mesa');
    },
    GetOneMesa(id) {
        return Api(secrets.URL_SPRING).get('mesa/' + id);
    },

}//export