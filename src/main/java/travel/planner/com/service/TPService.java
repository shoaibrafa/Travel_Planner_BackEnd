package travel.planner.com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import travel.planner.com.model.City;
import travel.planner.com.model.WeatherDTO;
import travel.planner.com.repository.TPRepo;
import travel.planner.com.utility.JacksonUtility;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TPService {
    private final TPRepo tpRepo;
    private final RestTemplate restTemplate;


    @Autowired
    TPService(TPRepo tpRepo, RestTemplate restTemplate){
        this.tpRepo = tpRepo;
        this.restTemplate = restTemplate;
    }

    public City getCityService(String city_name){
        City city = tpRepo.findByCity(city_name);
        return city;
    }

    public List<String> getAllCities(){
        List<City> cities = this.tpRepo.findAll();

        return cities.stream()
                .map(City::getCity)
                .collect(Collectors.toList());
    }


    public WeatherDTO getCityWeather(String city_name){
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city_name + "&APPID=7e10ba85b7309f2ac0d1dd4698bedd99";
        String result = restTemplate.getForObject(url, String.class);
        JacksonUtility jacksonUtility = new JacksonUtility();
        return jacksonUtility.getTemperature(result);
    }

}
