package pet.prjct.ydemy.ydemy.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pet.prjct.ydemy.ydemy.model.UserLogin;



public interface UserService extends UserDetailsService {

    void save(UserLogin userLogin);

    boolean containsUserByUsername(String username);

    boolean containsUserByEmail(String email);


}
