<template>
  <main class="container text-white">
    <div class="pt-4 mb-8 relative">
      <input 
      @keyup.enter="previewCity(searchQueary)"
      type="text" 
      placeholder="Search for a city" 
      v-model="searchQueary"
      class="py-2 px-1 w-full bg-transparent border-b focus:border-weather-secondary focus:outline-none
      focus:shadow-[0px_1px_0_0_#004E71]" >
    </div>  
    <div class="flex flex-col gap-4">
      <Suspense>
        <CityList>
          <template #fallback>
            <p>loading cities</p>
          </template>
        </CityList>
      </Suspense>
    </div>
  </main>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import CityList from '../components/CityList.vue';

const searchQueary = ref("");

const router = useRouter();
const previewCity = (searchResult) => {
  router.push({
    name: "cityView",
    params: {city: searchResult },
    query:{
      preview: true,
    }
  });
};

function sendToBackend(){
  fetch("/api/getWeather/hamburg")
          .then((res) =>res.json())
          .then((data) =>{
            console.log(data) 
          })
          .catch(err => console.log(err.message))
};
</script>
