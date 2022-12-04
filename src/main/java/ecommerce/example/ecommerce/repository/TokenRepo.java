package ecommerce.example.ecommerce.repository;

import ecommerce.example.ecommerce.model.AuthenticationToken;
import ecommerce.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
