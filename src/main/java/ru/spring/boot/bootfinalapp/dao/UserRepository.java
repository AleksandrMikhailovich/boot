package ru.spring.boot.bootfinalapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.boot.bootfinalapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
