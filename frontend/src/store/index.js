import Vuex from "vuex";

import { mesa } from './modules/client/mesa.js';
import { category } from './modules/client/category.js';
import { mesaDashboard } from './modules/dashboard/mesaDashboard.js';
import { categoryDashboard } from './modules/dashboard/categoryDashboard.js';

export default Vuex.createStore({

    modules: {
        mesa: mesa,
        category: category,
        mesaDashboard: mesaDashboard,
        categoryDashboard: categoryDashboard,
    }
});