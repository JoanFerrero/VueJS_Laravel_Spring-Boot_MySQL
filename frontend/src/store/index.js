import Vuex from "vuex";

import { mesa } from './modules/client/mesa.js';
import { category } from './modules/client/category.js';
import { user } from './modules/client/user.js';
import { mesaDashboard } from './modules/dashboard/mesaDashboard.js';
import { categoryDashboard } from './modules/dashboard/categoryDashboard.js';
import { userDashboard } from './modules/dashboard/userDashboard.js';
import { reservationDashboard } from './modules/dashboard/reservationDashboard.js';

export default Vuex.createStore({
    modules: {
        mesa: mesa,
        category: category,
        user: user,
        mesaDashboard: mesaDashboard,
        categoryDashboard: categoryDashboard,
        userDashboard: userDashboard,
        reservationDashboard: reservationDashboard,
    }
});