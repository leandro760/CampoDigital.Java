package com.app.users.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.shared.application.exception.HandlerException;

import com.app.users.domain.IUserRepository;
import com.app.users.domain.User;

@Repository
public class UserDatasource {

    private static final String USER_NOT_FOUND = "Usuario no encontrado con ID: ";
    private static final String ERROR_SAVING = "Error al guardar usuario: ";
    private static final String ERROR_UPDATING = "Error al actualizar usuario: ";
    private static final String ERROR_DELETING = "Error al eliminar usuario: ";
    private static final String ERROR_FINDING = "Error al obtener usuarios: ";
    private static final String ERROR_FINDING_BY_ID = "Error al obtener usuario por ID: ";

    private final IUserRepository userRepository;

    public UserDatasource(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new HandlerException(ERROR_FINDING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    public User findById(Long id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new HandlerException(USER_NOT_FOUND + id, "NOT_FOUND"));
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_FINDING_BY_ID + e.getMessage(), "DATABASE_ERROR");
        }
    }

    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new HandlerException(ERROR_SAVING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    public User update(User user, Long id) {
        try {
            User existingUser = findById(id);
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_UPDATING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    public void deleteById(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new HandlerException(USER_NOT_FOUND + id, "NOT_FOUND");
            }
            userRepository.deleteById(id);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_DELETING + e.getMessage(), "DATABASE_ERROR");
        }
    }
}
