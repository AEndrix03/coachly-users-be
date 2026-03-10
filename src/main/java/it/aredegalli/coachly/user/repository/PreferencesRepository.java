package it.aredegalli.coachly.user.repository;

import it.aredegalli.coachly.user.model.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PreferencesRepository extends JpaRepository<Preferences, UUID> {
}
