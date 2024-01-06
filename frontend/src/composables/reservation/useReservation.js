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

export const useReservationDelete = (id) => {
    const reservationDelete = ref({})
    ReservationService.DeleteReservation(id)
        .then(res => { reservationDelete.value = res.data })
        .catch(error => console.log(error))
    return reservationDelete;
}




