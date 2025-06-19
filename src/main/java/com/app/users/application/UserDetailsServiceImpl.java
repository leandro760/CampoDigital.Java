package com.app.users.application;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.users.domain.IUserRepository;
import com.app.users.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String USER_NOT_FOUND_MESSAGE = "Usuario no encontrado con email: ";
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "Error al buscar usuario por email: ";
    private static final String USER_NOT_FOUND_ERROR_MESSAGE_2 = "Error al cargar usuario: ";

    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = findUserByEmail(email);
            return createUserDetails(user);
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE_2 + e.getMessage());
        }
    }

    private User findUserByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            if (user == null) {
                throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE + email);
            }
            return user;
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE + e.getMessage());
        }
    }

    private UserDetails createUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList());
    }
} 