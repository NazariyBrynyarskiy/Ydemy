package pet.prjct.ydemy.ydemy.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pet.prjct.ydemy.ydemy.model.UserLogin;
import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.List;


public interface UserService extends UserDetailsService {

    void save(UserLogin userLogin);

    boolean containsUserByUsername(String username);

}
