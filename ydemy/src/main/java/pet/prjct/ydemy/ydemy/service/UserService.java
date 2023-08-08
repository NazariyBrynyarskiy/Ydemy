package pet.prjct.ydemy.ydemy.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pet.prjct.ydemy.ydemy.model.UserLogin;
import pet.prjct.ydemy.ydemy.model.entity.User;


public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(UserLogin userLogin);

    boolean containsUserByUsername(String username);

}
