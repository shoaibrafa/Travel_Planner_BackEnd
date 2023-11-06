package travel.planner.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.planner.com.model.City;

@Repository
public interface TPRepo extends JpaRepository<City, Integer> {

    //fetch city by city name
    City findByCity(String city_name);
}
