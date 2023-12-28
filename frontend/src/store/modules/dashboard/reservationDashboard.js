import Constant from '../../../Constant';
import ReservationServiceDashboard from '../../../services/laravel/ReservationService';

export const reservationDashboard = {
    namespaced: true,
    mutations: {
        [Constant.INITIALIZE_RESERVATION]: (state, payload) => {
            if (payload) {
                state.reservation = payload;
            }
        },
        [Constant.DELETE_ONE_RESERVATION]: (state, payload) => {
            if (payload) {
                state.reservation = state.reservation.filter((itemReservation) => itemReservation.id !== payload)
            }
        },
        [Constant.UPDATE_ONE_RESERVATION]: (state, payload) => {
            if (payload) {
                let index = (state.reservation || []).findIndex((item) => item.id == payload.id);
                if (index !== -1) {
                    state.reservation[index] = payload;
                }
            }
        }
    },//mutations
    actions: {
        [Constant.INITIALIZE_RESERVATION]: async (store) => {
            try {
                const response = await ReservationServiceDashboard.GetReservations();
                store.commit(Constant.INITIALIZE_RESERVATION, response.data.data);
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.DELETE_ONE_RESERVATION]: async (store, payload) => {
            try {
                await ReservationServiceDashboard.DeleteReservation(payload);
                store.commit(Constant.DELETE_ONE_RESERVATION, payload)
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.UPDATE_ONE_RESERVATION]: async (store, payload) => {
            try {
                const response = await ReservationServiceDashboard.PutReservation(payload);
                if (response.status == 200) {
                    store.commit(Constant.UPDATE_ONE_RESERVATION, payload);
                }
            } catch (error) {
                console.error(error)
            }
        }
    },//actions
    getters: {
        getReservations(state) {
            return state.reservation;
        }
    }//getters
}//export