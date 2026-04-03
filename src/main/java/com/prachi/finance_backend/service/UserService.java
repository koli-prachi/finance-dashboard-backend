package com.prachi.finance_backend.service;


import com.prachi.finance_backend.model.User;
import com.prachi.finance_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        user.setActive(true); // default active
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {

        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setRole(updatedUser.getRole());
        existing.setActive(updatedUser.getActive());
        return userRepository.save(existing);
    }
}
