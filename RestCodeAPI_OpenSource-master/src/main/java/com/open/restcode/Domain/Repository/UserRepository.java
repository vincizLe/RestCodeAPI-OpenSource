package com.open.restcode.Domain.Repository;

import com.open.restcode.Domain.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByName(String name);
}
