package com.ebook.repository;

import com.ebook.model.UserDtls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTests {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Mock data setup
        UserDtls user = new UserDtls();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setPassword("password123");

        // Mocking behavior of findByEmail
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
    }

    @Test
    public void findByEmailTest() {
        // Call the findByEmail method
        UserDtls foundUser = userRepository.findByEmail("test@example.com");

        // Verify the result
        assertEquals("test@example.com", foundUser.getEmail());
    }

    // You can add more tests for other CRUD operations if necessary
}