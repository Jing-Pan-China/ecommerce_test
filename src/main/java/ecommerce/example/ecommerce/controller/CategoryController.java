package ecommerce.example.ecommerce.controller;

import ecommerce.example.ecommerce.common.ApiResponse;
import ecommerce.example.ecommerce.model.Category;
import ecommerce.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public List<Category> listCategory(Category category) {
        return categoryService.listCategory();

    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Integer categoryId,  @RequestBody Category category) {
        // Check to see if the category exists.
        System.out.println("category id"+categoryId);
        if (!categoryService.findById(categoryId)) {
            // If the category exists then update it.
//            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }

        categoryService.editCategory(categoryId,category);

        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
    }


    @GetMapping("/hi")
    public String hi() {

        return "success";



    }
}

