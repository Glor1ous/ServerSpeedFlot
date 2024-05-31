package reg.example.SecServer.response;

import lombok.*;
import reg.example.SecServer.entity.FlightEntity;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
    private boolean success;
    private String message;
    private FlightEntity flight;
}
