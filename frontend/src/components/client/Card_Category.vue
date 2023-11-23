<template>
    <div class="card" style="width: 18rem;" @click="emitAction()">
        <img class="card-img-top" :src="category.photo">
        <div class="card-body">
            <h5 class="card-title">{{category.name_category}}</h5>
        </div>
    </div>
</template>

<script>
import { getCurrentInstance } from 'vue';
export default {
    props: {
        category: Object
    },
    emits: {
        emitAction: Object
    },
    setup(props) {
        const { emit } = getCurrentInstance();
        const emitAction = () => {
            emit('emitAction', props.category);
        }

        return { emitAction }
    }
}
</script>
