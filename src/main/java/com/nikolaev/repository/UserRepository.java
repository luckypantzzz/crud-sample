package com.nikolaev.repository;

import com.nikolaev.users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByLogin(String login);
    Optional<User> findUserById(Long id);
}
