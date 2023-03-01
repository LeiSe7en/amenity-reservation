package com.amentity_reservation_system.service;

import com.amentity_reservation_system.model.User;
import com.amentity_reservation_system.repos.UserRepository;
import com.amentity_reservation_system.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .collect(Collectors.toList());
    }

    public User get(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final User user) {
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final User user) {
        final User existingUser = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

}
