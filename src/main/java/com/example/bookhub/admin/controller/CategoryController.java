package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.service.CategoryService;
import com.example.bookhub.admin.vo.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String category(Model model) {
        List<Category> topLevelCategories = categoryService.getAllTopLevelCategories();
        List<Category> secondLevelCategories = categoryService.getAllSecondLevelCategories();
        List<Category> thirdLevelCategories = categoryService.getAllThirdLevelCategories();

        model.addAttribute("topLevelCategories", topLevelCategories);
        model.addAttribute("secondLevelCategories", secondLevelCategories);
        model.addAttribute("thirdLevelCategories", thirdLevelCategories);

        return "admin/category";
    }

    @GetMapping("/getSubCategories")
    @ResponseBody
    public List<Category> subCategories(@RequestParam("category") int categoryNo) {
        return categoryService.getSubCategoriesByCategoryNo(categoryNo);
    }

    @GetMapping("/getParentCategory")
    @ResponseBody
    public Category parentCategory(@RequestParam("category") int categoryNo) {
        return categoryService.getParentCategoryByCategoryNo(categoryNo);
    }

    @GetMapping("/getSecondCategories")
    @ResponseBody
    public List<Category> getSecondCategories() {
        return categoryService.getAllSecondLevelCategories();
    }

    @PostMapping("/addTopCategory")
    @ResponseBody
    public List<Category> addTopLevelCategory(@RequestParam("categoryName") String categoryName) {
        categoryService.addTopLevelCategory(categoryName);
        return categoryService.getAllTopLevelCategories();
    }

    @PostMapping("/addSubCategory")
    @ResponseBody
    public List<Category> addSubCategory(@RequestParam("categoryName") String categoryName,
                                         @RequestParam("topCategoryNo") int topCategoryNo) {
        categoryService.addSubCategory(categoryName, topCategoryNo);
        return categoryService.getSubCategoriesByCategoryNo(topCategoryNo);
    }

}
