package com.example.Bildergalerie.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface ExtendedRepository<T extends ExtendedEntity> extends JpaRepository<T, UUID> {

}
