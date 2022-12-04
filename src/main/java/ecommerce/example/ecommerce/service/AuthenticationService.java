package ecommerce.example.ecommerce.service;

import ecommerce.example.ecommerce.exceptions.AuthenticationFailException;
import ecommerce.example.ecommerce.model.AuthenticationToken;
import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepo tokenRepo;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }
    public User getUser(String token){
      final AuthenticationToken authenticationToken= tokenRepo.findByToken(token);
      if(Objects.isNull(authenticationToken)){
          return null;
      }
//      authenticationToken is not null
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthenticationFailException{
//        null check
        if(Objects.isNull(token)){
//            throw an exception
            throw new AuthenticationFailException("token not present");

        }

        if(Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("token not valid");
        }
    }
}
