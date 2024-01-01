<template>
  <carousel :items-to-show="1.5" :autoplay="4000" :wrap-around="true">
    <slide v-for="item in data" :key="item.id">
      <carouselItem class="carousel__item" :item="item" @emitAction="emitAction" />
    </slide>

    <template #addons>
      <navigation />
      <pagination />
    </template>
  </carousel>
</template>

<script>
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import { getCurrentInstance } from 'vue';
import carouselItem from './carrousel_item.vue';
export default {
  components: { carouselItem, Carousel, Slide, Pagination, Navigation },
  props: {
    data: Object
  },
  emits: {
    emitAction: Object
  },
  setup(props) {
    const { emit } = getCurrentInstance();
    const emitAction = (item) => {
        emit('emitAction', item);
    }

    return { emitAction }
  }
}
</script>
<style lang="scss">
@import 'vue3-carousel/dist/carousel.css';

.carousel__item {
    min-height: 200px;
    width: 100%;
    background-color: none;
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>