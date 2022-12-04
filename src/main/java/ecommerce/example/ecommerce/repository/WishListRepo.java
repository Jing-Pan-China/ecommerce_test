package ecommerce.example.ecommerce.repository;

import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepo extends JpaRepository<WishList,Integer> {
//    List<WishList>findAllByUserOrderByCreatedDateDesc(User user);

    List<WishList> findAllByUser(User user);
}
