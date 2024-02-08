package com.example.provider.repository;

import com.example.provider.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий клиентов
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Override
    Optional<Client> findById(Integer id);
}
