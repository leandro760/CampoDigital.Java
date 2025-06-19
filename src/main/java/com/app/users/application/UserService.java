package com.app.users.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.shared.application.exception.HandlerException;
import com.app.users.domain.IUserRepository;
import com.app.users.domain.IUserService;
import com.app.users.domain.User;

@Service
public class UserService implements IUserService {

    private static final String USER_NOT_FOUND = "Usuario no encontrado con ID: ";

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), "ERROR_GET_ALL_USERS");
        }
    }

    @Override
    public User findById(Long id) {
        try {
            
            return userRepository.findById(id).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND + id));
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), "ERROR_FIND_BY_ID");
        }
    }

    @Override
    @Transactional
    public User save(User user) {
        try {
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new RuntimeException("El email ya está registrado: " + user.getEmail());
            }
            return userRepository.save(user);
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), "ERROR_SAVE_USER");
        }
    }

    @Override
    @Transactional
    public User update(User user, Long id) {
        try {
            User existingUser = findById(id);
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), "ERROR_UPDATE_USER");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            User user = findById(id);
            userRepository.delete(user);
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), "ERROR_DELETE_USER");
        }
    }
}
