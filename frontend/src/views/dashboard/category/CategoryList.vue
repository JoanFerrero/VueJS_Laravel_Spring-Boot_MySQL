<template>
  <table class="table table-warning">
    <thead>
      <tr>
        <th scope="col">#ID</th>
        <th scope="col">Name</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="category in state.categories">
        <th scope="row">{{category.id}}</th>
        <td>{{category.name_category}}</td>
        <td>
          <button class="btn btn-primary" @click="updateCategory(category.id)">Edit</button>
        </td>
        <td>
          <button class="btn btn-danger" @click="deleteCategory(category.id)">Delete</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import { ref, onMounted, reactive, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '../../../Constant.js';
import { createToaster } from "@meforma/vue-toaster";

export default {
    components  : {},
    setup() {
      const store = useStore();
      const router = useRouter();
      const toaster = createToaster({ position: "top-right" });

      store.dispatch(`categoryDashboard/${Constant.INITIALIZE_CATEGORY}`);

      const updateCategory = (id) => {
        const category = state.categories.find(e => e.id === id);
        if (category) {
          router.push({ name: 'categoryupdate', params: { id } })
        } else {
          toaster.info('You have to select ONE category');
        }
      }
      const deleteCategory = (id) => {
        const category = state.categories.find(e => e.id === id);

        if (category) {
          store.dispatch(`categoryDashboard/${Constant.DELETE_CATEGORY}`, category.id);
        } else {
          toaster.info('You have to select at last ONE category');
        }
      }

      const state = reactive({
          categories: computed(() => store.getters['categoryDashboard/GetCategories'])
      });

      return { state, updateCategory, deleteCategory }
    }
}
</script>

<style>
body {
  background: #eee;
}

.icons i {
  color: #b5b3b3;
  border: 1px solid #b5b3b3;
  padding: 6px;
  margin-left: 4px;
  border-radius: 5px;
  cursor: pointer;
}

.activity-done {
  font-weight: 600;
}

.list-group li {
  margin-bottom: 12px;
}

.list-group-item {
}

.list li {
  list-style: none;
  padding: 10px;
  border: 1px solid #e3dada;
  margin-top: 12px;
  border-radius: 5px;
  background: #fff;
}

.checkicon {
  color: green;
  font-size: 19px;
}

.date-time {
  font-size: 12px;
}

.profile-image img {
  margin-left: 3px;
}
</style>