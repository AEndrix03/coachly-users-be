package it.aredegalli.coachly.user.repository;

import it.aredegalli.coachly.user.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
