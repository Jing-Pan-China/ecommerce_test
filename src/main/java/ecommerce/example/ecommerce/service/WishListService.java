package ecommerce.example.ecommerce.service;

import ecommerce.example.ecommerce.dto.ProductDto;
import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.model.WishList;
import ecommerce.example.ecommerce.repository.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    @Autowired
    WishListRepo wishListRepo;

    @Autowired
    ProductService productService;

    public void createWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepo.findAllByUser(user);
        List<ProductDto> productDtos =new ArrayList<>();
        for (WishList wishList:wishLists){

            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return productDtos;
    }

//    public ResponseEntity<List<ProductDto>> getWishListForUser(User user) {
//        List<WishList> findAllByUserOrOrderBy
//    }
}
