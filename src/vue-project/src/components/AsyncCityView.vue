<template>
    <div>

    </div>
</template>

<script setup>
import axios from 'axios';
import {useRoute} from "vue-router";


const route = useRoute();
const getWeatherData =async () => {
    try {
        const weatherData = axios.get(`/api/getWeather/${route.params.city}`);


        return weatherData;
    } catch (error) {
        console.log(error)
    }
};

const weatherData = await getWeatherData();
console.log(weatherData);

</script>
