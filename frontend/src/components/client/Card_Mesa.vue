<template>
    <div class="card" style="width: 20rem;" @click="showDetails(mesa.id)">
        <img class="card-img-top" :src="mesa.photo">
        <div class="card-body">
            <h5 class="card-title">{{mesa.name_mesa}}</h5>
            <p class="card-text">Capacidad: {{mesa.capacity}}</p>
            <p class="card-text" v-if="mesa.categories.length > 1">Horario: {{mesa.categories[0].name_category}}, {{mesa.categories[1].name_category}} ...</p>
            <p class="card-text" v-if="mesa.categories.length < 2">Categoria: {{mesa.categories[0].name_category}}</p>
        </div>
    </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
    props: {
        mesa: Object
    },
    setup(props) {
        const router = useRouter();

        const showDetails = (id) => {
            router.push({ name: "reservationDetails", params: { id } });
        }

        return { showDetails }

    }
}
</script>
