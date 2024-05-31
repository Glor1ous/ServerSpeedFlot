package reg.example.SecServer.response;

import lombok.*;
import reg.example.SecServer.entity.BookingEntity;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private boolean success;
    private String message;
    private BookingEntity ticket;
}
