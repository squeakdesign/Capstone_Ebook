package com.ebook.services;

import com.ebook.model.UserDtls;
import com.ebook.repository.UserRepository;
import com.ebook.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "John Doe, john@example.com, password123, encodedPassword1",
            "Jane Smith, jane@example.com, pass456, encodedPassword2"
    })
    public void testSaveUser(String name, String email, String password, String encodedPassword) {
        // Mock data
        UserDtls user = new UserDtls();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        // Call the method
        UserDtls savedUser = userService.saveUser(user);

        // Verify interactions and assertions
        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(user);
        assertEquals(encodedPassword, savedUser.getPassword());
        assertEquals("ROLE_USER", savedUser.getRole());
    }

    @Test
    public void testGetUserById() {
        // Mock data
        int userId = 1;
        UserDtls user = new UserDtls();
        user.setId(userId);
        user.setName("Jane Doe");

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.ofNullable(user));

        // Call the method
        UserDtls retrievedUser = userService.getUserById(userId);

        // Verify interactions and assertions
        verify(userRepository, times(1)).findById(userId);
        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getName(), retrievedUser.getName());
    }
}
