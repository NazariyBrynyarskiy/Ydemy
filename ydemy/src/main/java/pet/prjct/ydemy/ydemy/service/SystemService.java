package pet.prjct.ydemy.ydemy.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.prjct.ydemy.ydemy.dao.jparepo.RoleJpaRepository;
import pet.prjct.ydemy.ydemy.dao.jparepo.UserJpaRepository;
import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.Optional;

@Service
public class SystemService {

    private RoleJpaRepository roleJpaRepository;
    private UserJpaRepository userJpaRepository;

    @Autowired
    public SystemService(RoleJpaRepository roleJpaRepository,
                         UserJpaRepository userJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Transactional
    public String updateUserRole(String authority, String username) {

        Optional<User> userOptional = userJpaRepository.findById(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String userAuthority = user.getAuthority().getAuthority();

            if (!userAuthority.equals("ROLE_ADMIN")) {
                roleJpaRepository.updateUserRole(authority, username);

                return "'" + username + "' has a new role('" +
                        authority.replaceAll("ROLE_", "") +
                        "') now.";
            }

        }
        return "'" + username + "' does not exist";
    }

}
