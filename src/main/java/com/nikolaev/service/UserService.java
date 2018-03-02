package com.nikolaev.service;

import com.nikolaev.repository.UserRepository;
import com.nikolaev.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserByLogin(String login) throws UserNotFoundException {
        return userRepository.findUserByLogin(login)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User addUser(User user) throws UserNameDuplicateException {
        userRepository.findUserByLogin(user.getLogin())
                .ifPresent(user1 -> {throw new UserNameDuplicateException();});
        return userRepository.save(user);
    }

    public User updateUser(User user) throws UserNameDuplicateException {
        userRepository.findUserByLogin(user.getLogin())
                .filter(user1 -> !Objects.equals(user1.getId(), user.getId()))
                .ifPresent(user1 -> {throw new UserNameDuplicateException();});
        return userRepository.save(user);
    }

    public User block(User user) {
        if (user.isBlocked())
            return user;

        user.setBlocked(true);
        return userRepository.save(user);
    }

    public User unlock(User user) {
        if (!user.isBlocked())
            return user;

        user.setBlocked(false);
        return userRepository.save(user);
    }
}
