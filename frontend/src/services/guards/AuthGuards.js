import store from '../../store';
import Constant from '../../Constant';
import UserService from '../springboot/UserService';

export default {

    async authGuardAdmin(to, from, next) {
        try {
            if (localStorage.getItem('isAdmin')) {
                next();
                // Mirar resposta de la consulta a Laraven
                //const response = await UserService.isAdmin();
                //console.log(response)
                //if (response.status === 200) {
                //    next();
                //}
            } else {
                next('/login');
            }
        } catch (error) {
            store.dispatch(`user/${Constant.LOGOUT}`);

        }
    },

    async AuthGuard(to, from, next) {
        
    },

    async noAuthGuard(to, from, next) {
        if (!store.getters['user/GetIsAuth'] && !localStorage.getItem('isAuth')) {
            next();
        } else {
            next('/home');
        }
    }
}