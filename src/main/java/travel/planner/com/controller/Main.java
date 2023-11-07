package travel.planner.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import travel.planner.com.model.City;
import travel.planner.com.model.WeatherDTO;
import travel.planner.com.service.TPService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


@RestController
public class Main {

    private final TPService tpService;
    @Autowired
    Main(TPService tpService){
        this.tpService = tpService;
    }

    /**
     * This end point returns all city names. The city names
     * are consumed by the pull down menu on the front end.
     */
    @GetMapping("/all")
    public List<String> allCities(){
        return tpService.getAllCities();
    }


    /**
     * This end point returns a specific City by city name.
     */
    @GetMapping("/getcity/{city_name}")
    public City getCity(@PathVariable String city_name){
        return this.tpService.getCityService(city_name);
    }


    /**
     * This end point is responsible to return the related image
     * of the city as byte array.
     * @param city rtwrtq
     * @return
     * @throws IOException
     */
    @GetMapping("/image/{city}")
    public ResponseEntity<byte[]> getImage(@PathVariable String city) throws IOException {
        String paths = "src/main/resources/static/images/" + city.toLowerCase() + ".jpg";

        Resource imageResource = new FileSystemResource(paths);
        if (!imageResource.exists()) {
            return null;
        }
        byte[] imageBytes = Files.readAllBytes(imageResource.getFile().toPath());
        MediaType contentType = MediaType.IMAGE_JPEG;

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(imageBytes);
    }


    /**
     * this end point is returning the weather data as WeatherDTO object.
     * @param city
     * @return
     */
    @GetMapping("/temperature/{city}")
    public WeatherDTO getTemperature(@PathVariable String city){
        return tpService.getCityWeather(city);
    }
}
