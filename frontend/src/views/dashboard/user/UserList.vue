<template>
    <table class="table table-warning">
      <thead>
        <tr>
          <th scope="col">#ID</th>
          <th scope="col">Username</th>
          <th scope="col">Email</th>
          <th scope="col">Type</th>
          <th scope="col">Is Active</th>
          <th scope="col">Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in state.users">
          <th scope="row">{{user.id}}</th>
          <td>{{user.username}}</td>
          <td>{{user.email}}</td>
          <td>{{user.type}}</td>
          <td>{{user.is_active === 1 ? 'Activa' : 'No Activa' }}</td>
          <td>
            <button class="btn btn-danger" @click="deleteUser(user.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
</template>

<script>
import { reactive, computed } from 'vue';
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

    store.dispatch(`userDashboard/${Constant.INITIALIZE_USER}`);

    const deleteUser = (id) => {
      const user = state.users.find(e => e.id === id);
      if(user) {
        store.dispatch(`userDashboard/${Constant.DELETE_USER}`, user.id);
      } else {
        toaster.info('You have to select at last ONE user');
      }
    }

    const state = reactive({
      users: computed(() => store.getters["userDashboard/GetUsers"])
    })

    return { state, deleteUser }
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