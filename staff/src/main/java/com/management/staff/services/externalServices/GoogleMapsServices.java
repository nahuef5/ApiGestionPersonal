package com.management.staff.services.externalServices;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.management.staff.global.exceptions.MapsException;
import com.management.staff.models.Address;
import java.io.IOException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
@Service
//clase servicio de maps que generara las coordenadas de la direccion pasada en el objeto staff
public class GoogleMapsServices{
    @Value("${maps.province}")
    private String province;
    @Value("${maps.country}")
    private String country;
    @Autowired
    private GeoApiContext geoApiContext;
    public LatLng getCoordinates(Address address)
            throws ApiException, InterruptedException, IOException{
        String completAddress = address.getStreet()+" "
                +address.getNum()+ ", "
                +address.getNeighborhood()+", "
                +address.getCity()+", "
                +this.province+", "
                +this.country;
        GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, completAddress).await();
        if(results.length > 0) {
            return results[0].geometry.location;
        }
        else{
        throw new MapsException("No se encontraron coordenadas para la dirección proporcionada.");
        }
    }
    public String getAddressFromCoordinates(LatLng coordinates)throws ApiException, InterruptedException, IOException{
        GeocodingResult[] results= GeocodingApi.reverseGeocode(geoApiContext, coordinates).await();
        if(results.length > 0) {
            return results[0].formattedAddress;
        }
        else{
            throw new MapsException("No se encontró la direccion desde esas coordenadas.");
        }
    }
}