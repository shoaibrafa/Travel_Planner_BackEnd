package travel.planner.com.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import travel.planner.com.model.WeatherDTO;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This Utility class has one method which extracts
 * temp, temp_min, temp_max, day and date from the
 * json object returned from openweather api.
 * It returns WeatherDTO object.
 */

public class JacksonUtility {

    public WeatherDTO getTemperature(String json) {
        WeatherDTO weatherDTO = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);

            if (jsonNode.has("main") && jsonNode.has("dt")) {
                JsonNode mainNode = jsonNode.get("main");
                long dt = jsonNode.get("dt").asLong();

                double temp = mainNode.get("temp").asDouble();
                double tempMin = mainNode.get("temp_min").asDouble();
                double tempMax = mainNode.get("temp_max").asDouble();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dateFormat.format(new Date(dt * 1000L));
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                String day = dayFormat.format(new Date(dt * 1000L));

                String sky = jsonNode.get("weather").get(0).get("main").asText();

                weatherDTO = new WeatherDTO();
                weatherDTO.setDay(day);
                weatherDTO.setDate(date);
                weatherDTO.setTemp(temp);
                weatherDTO.setTemp_min(tempMin);
                weatherDTO.setTemp_max(tempMax);
                weatherDTO.setSky(sky);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherDTO;
    }
}


