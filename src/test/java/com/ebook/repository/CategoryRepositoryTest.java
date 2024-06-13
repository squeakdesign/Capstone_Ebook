package com.ebook.repository;

import com.ebook.model.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    public static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/?user=root&password=root3520");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS ecommerce_db");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create test database", e);
        }
    }

    @AfterAll
    public static void dropDatabase() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/?user=root&password=root3520");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP DATABASE IF EXISTS testdb");
        } catch (Exception e) {
            throw new RuntimeException("Failed to drop test database", e);
        }
    }

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    void testExistsByName() {
        Category category = new Category();
        category.setName("Fiction");
        category.setImageName("fiction.jpg");
        category.setIsActive(true);
        categoryRepository.save(category);

        boolean exists = categoryRepository.existsByName("Fiction");
        assertThat(exists).isTrue();

        boolean notExists = categoryRepository.existsByName("Non-Fiction");
        assertThat(notExists).isFalse();
    }

    @Test
    void testFindByIsActiveTrue() {
        Category activeCategory = new Category();
        activeCategory.setName("Fiction");
        activeCategory.setImageName("fiction.jpg");
        activeCategory.setIsActive(true);
        categoryRepository.save(activeCategory);

        Category inactiveCategory = new Category();
        inactiveCategory.setName("Non-Fiction");
        inactiveCategory.setImageName("nonfiction.jpg");
        inactiveCategory.setIsActive(false);
        categoryRepository.save(inactiveCategory);

        List<Category> activeCategories = categoryRepository.findByIsActiveTrue();
        assertThat(activeCategories).isNotNull();
        assertThat(activeCategories.size()).isEqualTo(1);
        assertThat(activeCategories.get(0).getName()).isEqualTo("Fiction");
    }
}