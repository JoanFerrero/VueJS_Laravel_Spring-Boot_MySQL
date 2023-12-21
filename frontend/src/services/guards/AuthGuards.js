import store from '../../store';
import Constant from '../../Constant';
import UserService from '../springboot/UserService';

export default {

    async authGuardAdmin(to, from, next) {
        try {
            if (localStorage.getItem('isAdmin')) {
                next();
            } else {
                next('/login');
            }
        } catch (error) {
            store.dispatch(`user/${Constant.LOGOUT}`);

        }
    },

    async AuthGuard(to, from, next) {
        if (localStorage.getItem('isAuth')) {
            await store.dispatch(`user/${Constant.INITIALIZE_PROFILE}`);
        }
        if (store.getters['user/GetIsAuth'] && localStorage.getItem('isAuth')) {
            next();
        } else {
            next('/home');
        }
    },

    async noAuthGuard(to, from, next) {
        if (!store.getters['user/GetIsAuth'] && !localStorage.getItem('isAuth')) {
            next();
        } else {
            next('/home');
        }
    }
}