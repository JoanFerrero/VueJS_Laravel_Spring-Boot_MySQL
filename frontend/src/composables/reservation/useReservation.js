import { ref } from 'vue';
import ReservationService from '../../services/springboot/ReservationService.js';

export const useReservationCreate = (data = {}, id) => {
    const reservation = ref([])
    ReservationService.PostReservation(data, id)
        .then(res => {reservation.value = res.data })
        .catch(error => console.log(error))
    return reservation;
};

export const useReservationList = () => {
    const reservations = ref([])
    ReservationService.GetReservation()
        .then(res => { reservations.value = res.data })
        .catch(error => console.log(error))
    return reservations;
};

export const useReservationUpdate = (data = {}) => {
    const reservationUpdate = ref([])
    ReservationService.PutReservation(data)
        .then(res => { reservationUpdate.value = res.data })
        .catch(error => console.log(error))
    return reservationUpdate;
}

export const useReservationDelete = (id) => {
    const reservationDelete = ref({})
    ReservationService.DeleteReservation(id)
        .then(res => { reservationDelete.value = res.data })
        .catch(error => console.log(error))
    return reservationDelete;
}




