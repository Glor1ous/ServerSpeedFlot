package reg.example.SecServer.controller;

import org.springframework.http.ResponseEntity;
import reg.example.SecServer.entity.BookingEntity;
import reg.example.SecServer.entity.ReportEntity;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<ReportEntity>> getAllBookings() {
        return ResponseEntity.ok(new ListResponse<>(true, "Отчеты", reportService.getAll()));
    }
}

