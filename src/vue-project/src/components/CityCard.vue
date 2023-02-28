<template>
    <div class="flex py-6 px-3 bg-weather-secondary rounded-md shadow-md cursor-pointer">
        <!--City-->
        <div class="flex flex-col flex-1">
            <h1 class="text-3xl">{{ city.weather.name }}</h1>
            <h3>{{ (city.weather.sys.country) }}</h3>
        </div>
        
        <!--City temp-->
        <div class="flex flex-col gap-2">
            <p class="text-3xl self-end">
                {{ Math.round(city.weather.main.temp) }}&deg;
            </p>
            <div class="flex gap-2">
                <span class="text-xs">
                    max:
                    {{ Math.round(city.weather.main.temp_max) }}&deg;
                    min:
                    {{ Math.round(city.weather.main.temp_min) }}&deg;
                </span>
            </div>
        </div>
    </div>
</template>

<script setup>
defineProps({
    city: {
        type: Object,
        default:() => {},
    },
});
</script>
