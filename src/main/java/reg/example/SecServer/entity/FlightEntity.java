package reg.example.SecServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "")
@Entity
@Table(name = "Flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Город_вылета", nullable = false)
    private String departureCity;

    @Column(name = "Город_прилёта", nullable = false)
    private String arrivalCity;

    @Column(name = "Дата_вылета", nullable = false)
    private String departureDate;

    @Column(name = "Цена рейса", nullable = false)
    private Integer price;
}





