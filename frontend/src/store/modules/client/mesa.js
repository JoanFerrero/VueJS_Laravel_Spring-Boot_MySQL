import Constant from '../../../Constant';
import MesaService from '../../../services/springboot/MesaService';

export const mesa = {
    namespaced: true,
    actions: {
        [Constant.INITIALIZE_MESA]: async (store) => {
            try {
                const response = await MesaService.GetMesas();
                store.commit(Constant.INITIALIZE_MESA, response.data);
            } catch (error) {
                console.error(error);
            }
        },
        [Constant.INITIALIZE_ONE_STATE_MESA]: async (store, payload) => {
            try {
                if (store.state.mesa != undefined) {
                    const index = store.state.mesa.findIndex(object => {
                        return object.id === parseInt(payload);
                    });
                    store.commit(Constant.INITIALIZE_ONE_STATE_MESA, store.state.mesa[index]);
                } else {
                    const response = await MesaService.GetOneMesa(payload);
                    store.commit(Constant.INITIALIZE_ONE_STATE_MESA, response.data);
                }
            } catch (error) {
                console.error(error)
            }
        },

    },//actions
    mutations: {
        [Constant.INITIALIZE_MESA]: (state, payload) => {
            if (payload) {
                state.mesa = payload;
            }
        },
        [Constant.INITIALIZE_MESA]: (state, payload) => {
            if (payload) {
                state.OneMesa = payload;
            }
        }
    },//mutations
    getters: {
        GetMesas(state) {
            return state.mesas;
        }
    }//getters
}//export