package com.ebook.services;

import com.ebook.model.Product;
import com.ebook.repository.ProductRepository;
import com.ebook.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        // Initialize a test product
        testProduct = new Product();
        testProduct.setId(1);
        testProduct.setTitle("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setCategory("Books");
        testProduct.setPrice(100.0);
        testProduct.setStock(10);
        testProduct.setImage("test_image.jpg");
        testProduct.setDiscount(10);
        testProduct.setDiscountPrice(90.0);
        testProduct.setIsActive(true);
    }



    @Test
    public void getAllProductsTest() {
        // Mock product list
        List<Product> productList = Arrays.asList(testProduct, new Product());

        // Mocking repository behavior
        when(productRepository.findAll()).thenReturn(productList);

        // Call service method
        List<Product> allProducts = productService.getAllProducts();

        // Verify repository method was called once
        verify(productRepository, times(1)).findAll();

        // Assert the returned list is not null and contains two products
        assertNotNull(allProducts);
        assertEquals(2, allProducts.size());
    }
}