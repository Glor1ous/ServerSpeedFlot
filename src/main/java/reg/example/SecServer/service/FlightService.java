package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reg.example.SecServer.entity.FlightEntity;
import reg.example.SecServer.entity.ReportEntity;
import reg.example.SecServer.entity.UserEntity;
import reg.example.SecServer.repo.FlightRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class FlightService {
    private final FlightRepo repo;

    public List<FlightEntity> getAll() {
        return repo.findAll();
    }

    public Optional<FlightEntity> findById(Long id) {
        return repo.findById(id);
    }



    public FlightEntity save(FlightEntity data) {
        return repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }


    public List<FlightEntity> findByParameters(String departureCity, String arrivalCity, String departureDate) {
        return repo.findByDepartureCityAndArrivalCityAndDepartureDate(departureCity, arrivalCity, departureDate);
    }

}
