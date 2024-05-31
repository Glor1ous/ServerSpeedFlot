package reg.example.SecServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightEntity flight;


}
