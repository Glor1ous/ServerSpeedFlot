package reg.example.SecServer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "")
@Entity
@Table(name = "Booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_User", nullable = false)
    private UserEntity user;



    @ManyToOne
    @JoinColumn(name = "ID_Flights", nullable = false)
    private FlightEntity flight;


}
