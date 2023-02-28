<template>
    <div class="flex flex-col flex-1 items-center">

            <!--Banner-->
            <div v-if="route.query.preview" class="text-white p-4 bg-weather-secondary w-full text-center">
                <p>Previewing this city</p>
            </div>
            <!--Weather data-->
            <div class="flex flex-col items-center text-white py-12">
                <h1 class="text-4xl mb-2">{{ route.params.city }}</h1>
                <p class="text-8xl mb-8">
                    {{ Math.round(weatherData.data.main.temp) }}&deg;
                </p>
                    <p >
                        Feels like
                    {{ Math.round(weatherData.data.main.feels_like) }}&deg;
                </p>
                <p class="capitalize">
                    {{ weatherData.data.weather[0].description }}
                    <!--"weatherData.data.weather.0.description" -->
                </p>
                <img
                class="w-[150px] h-auto"
                :src="`http://openweathermap.org/img/wn/${weatherData.data.weather[0].icon}@2x.png`"
                alt=""
                />
        </div>

        <hr class="border-white border-opacity-10 border w-full" />
        <!-- hour-->
    </div>
</template>

<script setup>
import axios from 'axios';
import {useRoute} from "vue-router";


const route = useRoute();
const getWeatherData =async () => {
    try {
        const weatherData = axios.get(`/api/getWeather/${route.params.city}`);
        
        //console.log((await weatherData).data.message);
        return weatherData;
    } catch (error) {
        console.log(error)
    }
};

const weatherData = await getWeatherData();
console.log(weatherData.data.main.temp);
//"weatherData.data.main.temp"
</script>
