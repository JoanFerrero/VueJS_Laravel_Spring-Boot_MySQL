import Constant from '../../../Constant';
import MesaService from "../../../services/springboot/MesaService";

export const mesa = {
    namespaced: true,
    mutations: {
        [Constant.INITIALIZE_MESA]: (state, payload) => {
            if (payload) {
                state.mesa = payload;
            }
        },
        [Constant.INITIALIZE_ONE_STATE_MESA]: (state, payload) => {
            if (payload) {
                state.OneMesa = payload;
            }
        },
        [Constant.INITIALIZA_MESA_RESERVATION]: (state, payload) => {
            if (payload) {
                state.mesaReservation = payload;
            }
        },
        [Constant.INITIALIZE_MESA_RELATION]: (state, payload) => {
            if (payload) {
                state.mesasRelation = payload;
            }
        },
    },//mutations
    actions: {
        [Constant.INITIALIZE_MESA]: async (store) => {
            try {
                const response = await MesaService.GetMesas();
                store.commit(Constant.INITIALIZE_MESA, response.data);
            } catch (error) {
                console.error(error)
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
        [Constant.INITIALIZA_MESA_RESERVATION]: async (store, payload) => {
            try {
                const response = await MesaService.GetMesasReservation(payload);
                store.commit(Constant.INITIALIZA_MESA_RESERVATION, response.data);
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.INITIALIZE_MESA_RELATION]: async (store, payload) => {
            try {
                const response = await MesaService.GetMesasRelation(payload);
                store.commit(Constant.INITIALIZE_MESA_RELATION, response.data);
            } catch (error) {
                console.error(error)
            }
        },
    },//actions
    getters: {
        getMesas(state) {
            return state.mesa;
        },
        getOneMesa(state) {
            return state.OneMesa;
        },
        getMesaReservation(state) {
            return state.mesaReservation;
        },
        getMesasRelation(state) {
            return state.mesasRelation;
        }
    }//getters
}//export