package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.generic.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for {@link User} entities.
 *
 * This interface extends JpaRepository and provides CRUD operations
 * for managing Photo entities in the database. It also includes a custom
 * method to find photos by the album's ID.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Luca Iacopetta
 */
public interface UserRepository extends ExtendedRepository<User> {
    public Optional<User> findById(UUID userId);
    Optional<User> findByEmail(String email);

}


