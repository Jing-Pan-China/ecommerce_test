package ecommerce.example.ecommerce.controller;

import ecommerce.example.ecommerce.common.ApiResponse;
import ecommerce.example.ecommerce.dto.ProductDto;
import ecommerce.example.ecommerce.model.Category;
import ecommerce.example.ecommerce.repository.CategoryRepo;
import ecommerce.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        final Optional<Category> optionalCategory=categoryRepo.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){

            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }

        productService.createProduct(productDto,optionalCategory.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category has been added"), HttpStatus.CREATED);

    }

//    @GetMapping("/")
//    public ResponseEntity<List<ProductDto>>getProducts(){
//        List<ProductDto>products=productService.getAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//
//    }
//
//    @PostMapping("/update/{productId}")
//    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,@RequestBody ProductDto productDto) throws Exception {
//        final Optional<Category> optionalCategory=categoryRepo.findById(productDto.getCategoryId());
//        if(!optionalCategory.isPresent()){
//
//            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
//        }
//
//        productService.updateProduct(productDto,productId);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
//
//    }


}
