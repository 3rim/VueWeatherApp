<template>
    <header class="sticky top-0 bg-weather-primary shadow-lg">
        <nav class="container flex flex-col sm:flex-row items-center gap-4 text-white
        py-6">
        <RouterLink :to="{name: 'home'}">
            <div class=" flex items-center gap-3 ">
                <i class="fa-solid fa-sun text-2xl"></i>
                <p class="text-2xl">The local Weather</p>
            </div>
        </RouterLink>

        <div class="flex gap-3 flex-1 justify-end">
            <i class="fa-solid fa-circle-info  text-xl hover:text-weather-secondary duration-150 cursor-pointer" @click="toggleModal"></i>
            <i class="fa-solid fa-plus text-xl  hover:text-weather-secondary duration-150 cursor-pointer"
            @click="addCity"
            v-if="route.query.preview"></i>
        </div>
        
        <BaseModal :modalActive="modalActive" @close-modal="toggleModal">
            <h1 class="text-black">This is a learning Project asdasdasdasdasdada</h1>
        </BaseModal>
        </nav>
    </header>
</template>

<script setup>
import { ref } from "vue";
import {RouterLink, useRoute, useRouter} from "vue-router";
import {uid} from "uid";
import BaseModal from "./BaseModal.vue";

const modalActive = ref(null);
const toggleModal = () => {
    modalActive.value = !modalActive.value;
};

const savedCities = ref([]);
const route = useRoute();
const router = useRouter();
const addCity = () => {
  if (localStorage.getItem("savedCities")) {
    savedCities.value = JSON.parse(
      localStorage.getItem("savedCities")
    );
  }
  const locationObj = {
    id: uid(),
    city: route.params.city,
  };
  savedCities.value.push(locationObj);
  localStorage.setItem(
    "savedCities",
    JSON.stringify(savedCities.value)
  );
  let query = Object.assign({}, route.query);
  delete query.preview;
  router.replace({ query });
};
</script>