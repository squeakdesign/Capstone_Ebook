package com.ebook.repository;

import com.ebook.model.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

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
        productRepository.deleteAll();
    }

    @Test
    public void testFindByIsActiveTrue() {
        // Create test data
        Product product1 = new Product(1, "Product 1", "Description 1", "Category A", 100.0, 10, "image1.jpg", 0, 100.0, true);
        Product product2 = new Product(2, "Product 2", "Description 2", "Category B", 150.0, 15, "image2.jpg", 0, 150.0, true);
        Product product3 = new Product(3, "Product 3", "Description 3", "Category A", 200.0, 20, "image3.jpg", 0, 200.0, true);

        // Save products to repository
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // Invoke repository method
        List<Product> activeProducts = productRepository.findByIsActiveTrue();

        // Assertions
        Assertions.assertEquals(2, activeProducts.size());
        Assertions.assertTrue(activeProducts.stream().allMatch(Product::getIsActive));
    }

    @Test
    public void testFindByCategory() {
        // Create test data
        Product product1 = new Product(1, "Product 1", "Description 1", "Category A", 100.0, 10, "image1.jpg", 0, 100.0, true);
        Product product2 = new Product(2, "Product 2", "Description 2", "Category B", 150.0, 15, "image2.jpg", 0, 150.0, true);
        Product product3 = new Product(3, "Product 3", "Description 3", "Category A", 200.0, 20, "image3.jpg", 0, 200.0, true);
        // Save products to repository
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // Invoke repository method
        List<Product> productsInCategoryA = productRepository.findByCategory("Category A");

        // Assertions
        Assertions.assertEquals(2, productsInCategoryA.size());
        Assertions.assertTrue(productsInCategoryA.stream().allMatch(p -> p.getCategory().equals("Category A")));
    }
}