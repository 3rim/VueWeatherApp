<template>
    <div class="flex flex-col flex-1 items-center">

            <!--Banner-->
            <div v-if="route.query.preview" class="text-white p-4 bg-weather-secondary w-full text-center">
                <p>Previewing this city. To start tracking click the "+" icon</p>
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

        <div class="flex items-center gap-2 py-12 text-white cursor-pointer duration-150 hover:text-red-600"
        @click="removeCity"
        v-if="!route.query.preview">
            <i class="fa-solid fa-trash"></i>
            <p>Remove City from tracking</p>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import {useRoute, useRouter} from "vue-router";


const route = useRoute();
const getWeatherData =async () => {
    try {
        const weatherData = axios.get(`/api/getWeather/${route.params.city}`);
        //TODO: City not found 
        
        //console.log((await weatherData).data.message);
        return weatherData;
    } catch (error) {
        console.log(error)
    }
};

const weatherData = await getWeatherData();

const router = useRouter();
const removeCity = () =>{
    const cities = JSON.parse(localStorage.getItem("savedCities"));
    const updatedCities = cities.filter(
        (city) => city.id !== route.query.id
        );

    localStorage.setItem("savedCities",JSON.stringify(updatedCities));
    router.push({
        name: "home",
    });
};
</script>
