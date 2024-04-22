package com.example.bookhub.admin.service;

import com.example.bookhub.admin.mapper.CategoryMapper;
import com.example.bookhub.admin.validator.Validator;
import com.example.bookhub.admin.vo.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;

    public List<Category> getAllTopLevelCategories() {
        return categoryMapper.getAllTopLevelCategories();
    }

    public List<Category> getAllSecondLevelCategories() {
        return categoryMapper.getAllSecondLevelCategories();
    }

    public List<Category> getAllThirdLevelCategories() {
        return categoryMapper.getAllThirdLevelCategories();
    }

    public List<Category> getSubCategoriesByCategoryNo(int categoryNo) {
        return categoryMapper.getSubCategoriesByCategoryNo(categoryNo);
    }

    public Category getParentCategoryByCategoryNo(int categoryNo) {
        return categoryMapper.getParentCategoryByCategoryNo(categoryNo);
    }

    public void addTopLevelCategory(String categoryName) {
        Validator.isCategoryNameUnique(categoryName, categoryMapper);
        categoryMapper.addTopLevelCategory(categoryName);
    }

    public void addSubCategory(String categoryName, int categoryNo) {
        Validator.isCategoryNameUnique(categoryName, categoryMapper);
        categoryMapper.addSubCategory(categoryName, categoryNo);
    }

    public Category getTopLevelCategoryByCategoryName(String categoryName) {
        return categoryMapper.getTopLevelCategoryByCategoryName(categoryName);
    }

    public Category getSubLevelCategoryByCategoryNameAndSuperCategoryNo(String categoryName, int categoryNo) {
        return categoryMapper.getSubLevelCategoryByCategoryNameAndSuperCategoryNo(categoryName, categoryNo);
    }
}