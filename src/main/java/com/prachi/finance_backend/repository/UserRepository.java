package com.prachi.finance_backend.repository;


import com.prachi.finance_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}