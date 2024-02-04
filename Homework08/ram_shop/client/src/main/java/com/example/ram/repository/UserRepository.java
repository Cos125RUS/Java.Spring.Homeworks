package com.example.ram.repository;

import com.example.ram.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);
}
