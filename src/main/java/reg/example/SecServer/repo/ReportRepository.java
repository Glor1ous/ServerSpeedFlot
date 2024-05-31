package reg.example.SecServer.repo;

import reg.example.SecServer.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}