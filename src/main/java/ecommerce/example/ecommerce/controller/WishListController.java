package ecommerce.example.ecommerce.controller;

import ecommerce.example.ecommerce.common.ApiResponse;
import ecommerce.example.ecommerce.dto.ProductDto;
import ecommerce.example.ecommerce.model.Product;
import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.model.WishList;
import ecommerce.example.ecommerce.service.AuthenticationService;
import ecommerce.example.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token")String token){
//        authenticate the token
        authenticationService.authenticate(token);
//        find the user
        User user= authenticationService.getUser(token);
//      save the item in wishList
        WishList wishList = new WishList(user,product);
        wishListService.createWishList(wishList);
        ApiResponse apiResponse= new ApiResponse(true,"Added to wishList");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

//    get all wishlist item for a user
    @GetMapping("/{token}")



    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token")String token){

        authenticationService.authenticate(token);
//        find the user
        User user= authenticationService.getUser(token);

        List<ProductDto> productDtos= wishListService.getWishListForUser(user);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }


//    save product in wishlist item
//    get all wishlist item for a user

}
