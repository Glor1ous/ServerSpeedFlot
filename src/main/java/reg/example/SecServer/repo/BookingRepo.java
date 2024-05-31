package reg.example.SecServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import reg.example.SecServer.entity.BookingEntity;

import java.util.List;

public interface BookingRepo extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByUserId(Long userId);
}

