package reg.example.SecServer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.FlightEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.FlightService;

import java.util.List;

@Tag(name="Рейсы", description="Контроллер для работы с рейсами")
@RequestMapping("api/v1/flight")
@RestController
@AllArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @Operation(summary = "Получить все рейсы")
    @GetMapping("/all")
    public ResponseEntity<ListResponse<FlightEntity>> getAllFlights() {
        return ResponseEntity.ok(new ListResponse<>(true, "Рейсы", flightService.getAll()));
    }


/*
  @Operation(summary = "Найти рейс по ID")
    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<FlightEntity>> findFlightById(@RequestParam Long id) {

        try {
            if (flightService.findById(id).isPresent()) {
                return ResponseEntity.ok(new DataResponse<FlightEntity>(true, "Рейс по id = " + id, flightService.findById(id).orElseThrow()));
            } else {
                return ResponseEntity.ok(new DataResponse<FlightEntity>(false, "Рейс не был найден "));
            }
        } catch (RuntimeException  e) {
            return ResponseEntity.ok(new DataResponse<FlightEntity>(false, "Рейс не был найден: " + e.getMessage()));
        }
    }
*/

    //http://localhost:28245/api/v1/flight/find?departureCity=Белореченск&arrivalCity=Краснодар&departureDate=20.05.2024
    @Operation(summary = "Найти рейсы по параметрам")
    @GetMapping("/find")
    public ResponseEntity<DataResponse<List<FlightEntity>>> findFlight(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam String departureDate) {

        try {
            List<FlightEntity> flights = flightService.findByParameters(departureCity, arrivalCity, departureDate);
            if (!flights.isEmpty()) {
                return ResponseEntity.ok(new DataResponse<>(true, "Рейсы найдены", flights));
            } else {
                return ResponseEntity.ok(new DataResponse<>(false, "Рейсы не были найдены"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<>(false, "Ошибка при поиске рейсов: " + e.getMessage()));
        }
    }





    @Operation(summary = "Обновить рейс ")
    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_flight(@RequestBody FlightEntity flight) {
        try {
            if (flightService.findById(flight.getId()).isPresent()) {
                flightService.save(flight);
                return ResponseEntity.ok(new BaseResponse(true, "Рейс обновлён"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Рейс не был обновлен"));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Рейс не был обновлен"));
        }
    }



    @Operation(summary = "Создать новый рейс")
    @PostMapping("/create")
    public ResponseEntity<DataResponse<FlightEntity>> createFlight(@RequestBody FlightEntity flight) {
        return ResponseEntity.ok(new DataResponse<FlightEntity>(true, "Рейс создан", flightService.save(flight)));

    }

    @Operation(summary = "Удалить рейс по ID")
    @DeleteMapping("/del{id}")
    public ResponseEntity<DataResponse <FlightEntity>> deleteBy_id(@RequestParam Long id) {
        try {
            if (flightService.findById(id).isPresent()) {
                flightService.delete(id);
                return ResponseEntity.ok(new DataResponse<FlightEntity>(true, "Рейс был удален"));
            } else {
                return ResponseEntity.ok(new DataResponse<FlightEntity>(false, "Рейс не был найден и не был удален"));
            }
        } catch (RuntimeException  e) {
            return ResponseEntity.ok(new DataResponse<FlightEntity>(false, "Рейс не был удален: " + e.getMessage()));
        }
}}
