package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reg.example.SecServer.entity.BookingEntity;
import reg.example.SecServer.entity.ReportEntity;
import reg.example.SecServer.repo.BookingRepo;
import reg.example.SecServer.repo.ReportRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class BookingService {
    private final BookingRepo bookingRepo;

    @Autowired
    private ReportRepository reportRepository;

    private final ReportRepository reportRepo;
    private ReportService reportService;

    public List<BookingEntity> getAll() {
        return bookingRepo.findAll();
    }

    public Optional<BookingEntity> findById(Long id) {
        return bookingRepo.findById(id);
    }

    public List<BookingEntity> findByUserId(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    public BookingEntity save(BookingEntity data) {
        return bookingRepo.save(data);
    }

    public void delete(Long id) {
        bookingRepo.deleteById(id);
    }


}
