package reg.example.SecServer.repo;


        import org.springframework.data.jpa.repository.JpaRepository;
        import reg.example.SecServer.entity.FlightEntity;
        import reg.example.SecServer.entity.UserEntity;

        import java.util.List;
        import java.util.Optional;

public interface FlightRepo extends JpaRepository<FlightEntity, Long> {
        List<FlightEntity> findByDepartureCityAndArrivalCityAndDepartureDate(String departureCity, String arrivalCity, String departureDate);
}
