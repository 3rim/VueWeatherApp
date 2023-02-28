<template>
    <div v-for="city in savedCities" :key="city.id">
        <CityCard :city="city" @click="goToCityView(city)"/>
    </div>
    <p v-if="savedCities.length ===0">
    No Cities added yet. To start tracking search a city and click the "+" icon
    </p>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import CityCard from './CityCard.vue';

const savedCities = ref([]);
const getCities = async () => {
    if(localStorage.getItem("savedCities")){
        savedCities.value = JSON.parse(localStorage.getItem("savedCities"));
        
        const request = [];
        savedCities.value.forEach((city)=>{
            request.push(
                axios.get(`/api/getWeather/${city.city}`)
            );
        });

        //containts data of all requests
        const weatherData = await Promise.all(request);
        weatherData.forEach((value,index)=> {
            savedCities.value[index].weather = value.data;
        });
    }
};
await getCities();

const router = useRouter();
const goToCityView = (city) =>{
    console.log(city);
    router.push({
        name:"cityView",
        params: {city:city.city},
        query: {}
    })
};


</script>