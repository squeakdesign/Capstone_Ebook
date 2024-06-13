package com.ebook.services;

import com.ebook.model.Category;
import com.ebook.repository.CategoryRepository;
import com.ebook.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        Category categoryToSave = new Category(1, "Test Category","test_image.jpg", true);
        when(categoryRepository.save(categoryToSave)).thenReturn(categoryToSave);

        Category savedCategory = categoryService.saveCategory(categoryToSave);

        assertNotNull(savedCategory);
        assertEquals("Test Category", savedCategory.getName());
        assertTrue(savedCategory.getIsActive());

        verify(categoryRepository, times(1)).save(categoryToSave);
    }

    @Test
    public void testGetAllCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Category 1","test_image.jpg", true));
        categories.add(new Category(2, "Category 2","test_image.jpg", true));
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> retrievedCategories = categoryService.getAllCategory();

        assertEquals(2, retrievedCategories.size());
        assertEquals("Category 1", retrievedCategories.get(0).getName());
        assertEquals("Category 2", retrievedCategories.get(1).getName());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testExistCategory() {
        when(categoryRepository.existsByName("Test Category")).thenReturn(true);

        assertTrue(categoryService.existCategory("Test Category"));
        assertFalse(categoryService.existCategory("Nonexistent Category"));

        verify(categoryRepository, times(1)).existsByName("Test Category");
        verify(categoryRepository, times(1)).existsByName("Nonexistent Category");
    }

    @Test
    public void testDeleteCategory() {
        Category categoryToDelete = new Category(1, "Category to delete","test_image.jpg", true);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryToDelete));

        assertTrue(categoryService.deleteCategory(1));

        verify(categoryRepository, times(1)).findById(1);
        verify(categoryRepository, times(1)).delete(categoryToDelete);
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category(1, "Test Category","test_image.jpg", true);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        Category retrievedCategory = categoryService.getCategoryById(1);

        assertNotNull(retrievedCategory);
        assertEquals("Test Category", retrievedCategory.getName());

        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllActiveCategory() {
        List<Category> activeCategories = new ArrayList<>();
        activeCategories.add(new Category(1, "Active Category 1", "test_image.jpg",true));
        activeCategories.add(new Category(2, "Active Category 2","test_image.jpg", true));
        when(categoryRepository.findByIsActiveTrue()).thenReturn(activeCategories);

        List<Category> retrievedCategories = categoryService.getAllActiveCategory();

        assertEquals(2, retrievedCategories.size());
        assertEquals("Active Category 1", retrievedCategories.get(0).getName());
        assertEquals("Active Category 2", retrievedCategories.get(1).getName());

        verify(categoryRepository, times(1)).findByIsActiveTrue();
    }

    @Test
    public void testGetCategoriesByName() {
        List<Category> categoriesWithName = new ArrayList<>();
        categoriesWithName.add(new Category(1, "Category 1","test_image.jpg", true));
        when(categoryRepository.findByNameContaining("Category")).thenReturn(categoriesWithName);

        List<Category> retrievedCategories = categoryService.getCategoriesByName("Category");

        assertEquals(1, retrievedCategories.size());
        assertEquals("Category 1", retrievedCategories.get(0).getName());

        verify(categoryRepository, times(1)).findByNameContaining("Category");
    }
}