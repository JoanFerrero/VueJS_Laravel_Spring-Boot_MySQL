<template>
    <div v-if="!isAdmin">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-3">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                
                <div class=" collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav ms-auto ">
                        <li class="nav-item">
                            <a class="nav-link mx-2 active" aria-current="page" href="/home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link mx-2" href="/reservation">Reservation</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link mx-2" href="/dashboard">DashBoard</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <RouterView />
    </div>
</template>

<script>
import { RouterView } from 'vue-router';
import { computed } from 'vue'

export default {
    components: {},
    computed: {
      isAdmin() {
        const path = this.$route.path.split('/');
        return path[1] == 'dashboard';
      },
  },
}
</script>
