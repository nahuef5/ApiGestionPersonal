package com.management.staff.global.configs.mapsConfig;
import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
@Configuration
public class MapsApiConfig{
    @Value("${google.maps.apiKey}")
    private String apiKey;
    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder().apiKey(apiKey).build();
    }
}