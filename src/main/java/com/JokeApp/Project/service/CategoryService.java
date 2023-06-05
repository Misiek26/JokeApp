package com.JokeApp.Project.service;

import com.JokeApp.Project.model.Category;
import com.JokeApp.Project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getAllCategoriesSortedByName() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return categoryRepository.findAll(sort);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(updatedCategory.getName());
            return categoryRepository.save(existingCategory);
        }
        return null; // Or throw an exception indicating that the category was not found
    }

    public void deleteCategory(Long id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException("Category with this id is not exists");
        }
    }
}
