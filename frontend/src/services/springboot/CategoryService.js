import Api from "../api";
import secrets from "../../secrets";

export default {
    GetCategories() {
        return Api(secrets.URL_SPRING).get('category');
    },
    GetOneCategory(id) {
        return Api(secrets.URL_SPRING).get('category/' + id);
    },
}//export