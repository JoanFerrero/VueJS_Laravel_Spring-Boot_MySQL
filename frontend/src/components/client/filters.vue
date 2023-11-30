<template>
  <section id="sidebar">
    <div>
      <h6 class="p-1 border-bottom">Nombre</h6>
      <ul class="list-group">
        <search :filterName="state.filters.name_mesa" @emitSearch="searchName"/>
      </ul>
      <h6 class="p-1 border-bottom">Capacidad</h6>
      <ul class="list-group">
        <input type="number" min="0" v-model="state.filters.capacity">
      </ul>
      <h6 class="p-1 border-bottom">Orden</h6>
      <ul class="list-group">
        <select v-model="state.filters.order" class="select_order">
          <option :value="0" disabled hidden selected>Capacity Order</option>
          <option :value="1">Ascendente</option>
          <option :value="2">Descendente</option>
        </select>
      </ul>
      <h6 class="p-1 border-bottom">Horario</h6>
      <ul class="list-group">
        <v-select multiple v-model="state.filters.categories" :options="state.categories" />
      </ul>
      <button @click="sendSubmit()">Filtrar</button>
      <button @click="deleteFilters()">Borrar</button>
    </div>
  </section>
</template>

<script>
import { reactive, getCurrentInstance, computed } from 'vue';
import Constant from '../../Constant';
import { useStore } from 'vuex';
import search from './search.vue';
export default {
  components: {search},
  props: {
    filters: Object
  },
  emits: {
    emitAction: Object
  },
  setup(props) {
    const store = useStore();
    store.dispatch(`category/${Constant.INITIALIZE_CATEGORY}`);

    const { emit } = getCurrentInstance();

    const state = reactive({
      categories: computed(() => store.getters['category/GetCategories']?.map(item => item.name_category)),
      filters: { ...props.filters}
    });

    const sendSubmit = () => {
      emit("onFilters", state.filters);
    }

    const searchName = (name) => {
      state.filters.name_mesa = name;
      emit("onFilters", state.filters);
    }

    const deleteFilters = () => {
      state.filters.categories = [];
      state.filters.order = 0;
      state.filters.capacity = 0;
      state.filters.page = 1;
      state.filters.limit = 3;
      emit("onDelete", state.filters);
    }

    return { state, sendSubmit, deleteFilters, searchName }
  }
}
</script>

<style lang="scss">
@import '../../../node_modules/vue-select/dist/vue-select.css';
*{
    box-sizing: border-box;
}
body{
    color: grey;
}
#sidebar{
    width: 20%;
    padding: 10px;
    margin: 0;
    float: left;
}
#products{
    width: 80%;
    padding: 10px;
    margin: 0;
    float: right;
}
ul{
    list-style: none;
    padding: 5px;
}
li a{
    color: darkgray;
    text-decoration: none;
}
li a:hover{
    text-decoration: none;
    color: darksalmon;
}
.fa-circle{
    font-size: 20px;
}
#red{
    color: #e94545d7;
}
#teal{
    color: rgb(69, 129, 129);
}
#blue{
    color: #0000ff;
}
.card{
    width: 250px;
    display: inline-block;
    height: 300px;
}
.card-img-top{
    width: 250px;
    height: 210px;
}
.card-body p{
    margin: 2px;
}
.card-body{
    padding: 0;
    padding-left: 2px;
}
.filter{
    display: none;
    padding: 0;
    margin: 0;
}

@media(min-width:991px){
    .navbar-nav{
        margin-left: 35%;
    }
    .nav-item{
        padding-left: 20px;
    }
    .card{
        width: 190px;
        display: inline-block;
        height: 300px;
    }
    .card-img-top{
        width: 188px;
        height: 210px;
    }
    #mobile-filter{
        display: none;
    }
}
@media(min-width:768px) and (max-width:991px){
    .navbar-nav{
        margin-left: 20%;
    }
    .nav-item{
        padding-left: 10px;
    }
    .card{
        width: 230px;
        display: inline-block;
        height: 300px;
        margin-bottom: 10px;
    }
    .card-img-top{
        width: 230px;
        height: 210px;
    }
    #mobile-filter{
        display: none;
    }
}
@media(min-width:568px) and (max-width:767px){
    .navbar-nav{
        margin-left: 20%;
    }
    .nav-item{
        padding-left: 10px;
    }
    .card{
        width: 205px;
        display: inline-block;
        height: 300px;
        margin-bottom: 10px;
    }
    .card-img-top{
        width: 203px;
        height: 210px;
    }
    .fa-circle{
        font-size: 15px;
    }
    #mobile-filter{
        display: none;
    }
}
@media(max-width:567px){
    .navbar-nav{
        margin-left: 0%;
    }
    .nav-item{
        padding-left: 10px;
    }
    #sidebar{
        width: 100%;
        padding: 10px;
        margin: 0;
        float: left;
    }
    #products{
        width: 100%;
        padding: 5px;
        margin: 0;
        float: right;
    }
    .card{
        width: 230px;
        display: inline-block;
        height: 300px;
        margin-bottom: 10px;
        margin-top: 10px;
    }
    .card-img-top{
        width: 230px;
        height: 210px;
    }
    .list-group-item{
        padding: 3px;
    }
    .offset-1{
        margin-left: 8%;
    }
    .filter{
        display: block;
        margin-left: 70%;
        margin-top: 2%;
    }
    #sidebar{
        display: none;
    }
    #mobile-filter{
        padding: 10px;
    }
}
</style>