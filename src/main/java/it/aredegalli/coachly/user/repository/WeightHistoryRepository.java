package it.aredegalli.coachly.user.repository;

import it.aredegalli.coachly.user.model.WeightHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WeightHistoryRepository extends JpaRepository<WeightHistory, UUID> {

    List<WeightHistory> findAllByUserIdOrderByRecordedAtDesc(UUID userId);

    Optional<WeightHistory> findFirstByUserIdOrderByRecordedAtDesc(UUID userId);
}
