<template>
    <div class="card" style="width: 18rem;">
        <img class="card-img-top" :src="mesa.photo">
        <div class="card-body">
            <h5 class="card-title">{{mesa.name_mesa}}</h5>
            <p class="card-text">Capacidad: {{mesa.capacity}}</p>
            <p class="card-text">Categoria: {{mesa.categories[0].name_category}}</p>
            <a href="#" class="btn btn-primary">Reservas</a>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        mesa: Object
    }
}
</script>
