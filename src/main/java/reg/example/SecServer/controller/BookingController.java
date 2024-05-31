package reg.example.SecServer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.BookingEntity;
import reg.example.SecServer.entity.FlightEntity;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.BookingService;

import java.util.List;
import java.util.Optional;

@Tag(name="Бронирование", description="Контроллер для работы с бронированием")
@RequestMapping("api/v1/booking")
@RestController
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @Operation(summary = "Получить все бронирования")
    @GetMapping("/all")
    public ResponseEntity<ListResponse<BookingEntity>> getAllBookings() {
        return ResponseEntity.ok(new ListResponse<>(true, "Бронирования", bookingService.getAll()));
    }

    @Operation(summary = "Получить бронирования по ID пользователя")
    @GetMapping("/userBookings")
    public ResponseEntity<ListResponse<BookingEntity>> getAllBookingsByUserId(@RequestParam Long id) {
        List<BookingEntity> bookings = bookingService.findByUserId(id);
        return ResponseEntity.ok(new ListResponse<>(true, "Бронирования пользователя с ID = " + id, bookings));
    }

    @Operation(summary = "Найти бронирование по ID")
    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<BookingEntity>> findBookingById(@RequestParam Long id) {
        try {
            Optional<BookingEntity> booking = bookingService.findById(id);
            if (booking.isPresent()) {
                return ResponseEntity.ok(new DataResponse<>(true, "Бронирование по id = " + id, booking.get()));
            } else {
                return ResponseEntity.ok(new DataResponse<>(false, "Бронирование не найдено"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<>(false, "Бронирование не найдено: " + e.getMessage()));
        }
    }

    @Operation(summary = "Создать новое бронирование")
    @PostMapping("/add")
    public ResponseEntity<DataResponse<BookingEntity>> createBooking(@RequestBody BookingEntity booking) {
        return ResponseEntity.ok(new DataResponse<>(true, "Бронирование создано", bookingService.save(booking)));
    }

    @Operation(summary = "Удалить бронирование по ID")
    @DeleteMapping("/del{id}")
    public ResponseEntity<DataResponse<BookingEntity>> deleteBookingById(@RequestParam Long id) {
        try {
            if (bookingService.findById(id).isPresent()) {
                bookingService.delete(id);
                return ResponseEntity.ok(new DataResponse<>(true, "Бронирование по id = " + id + " удалено"));
            } else {
                return ResponseEntity.ok(new DataResponse<>(false, "Рейс не был найден и не был удален"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<>(false, "Рейс не был удален: " + e.getMessage()));
        }
    }
}