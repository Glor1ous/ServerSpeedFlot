package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import reg.example.SecServer.entity.BookingEntity;
import reg.example.SecServer.entity.FlightEntity;
import reg.example.SecServer.entity.ReportEntity;
import reg.example.SecServer.entity.UserEntity;
import reg.example.SecServer.repo.ReportRepository;
import reg.example.SecServer.repo.ReportRepository;
import reg.example.SecServer.response.ListResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepository reportRepo;
    public ListResponse<ReportEntity> getAllReports() {
        List<ReportEntity> reports = reportRepo.findAll();
        return new ListResponse<>(true, "Отчеты успешно получены", reports);
    }

    public List<ReportEntity> getAll() {
        return reportRepo.findAll();
    }

    public void createReport(UserEntity userId, FlightEntity flightId) {
        ReportEntity report = new ReportEntity();
        report.setUser(userId);
        report.setFlight(flightId);

        reportRepo.save(report);
    }


}
