package pet.prjct.ydemy.ydemy.service.impl;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.dao.RoleJpaRepository;
import pet.prjct.ydemy.ydemy.dao.UserJpaRepository;
import pet.prjct.ydemy.ydemy.model.entity.Authority;
import pet.prjct.ydemy.ydemy.model.entity.User;
import pet.prjct.ydemy.ydemy.service.SystemService;

import java.util.Optional;

@Repository
public class SystemServiceImpl implements SystemService {

    private RoleJpaRepository roleJpaRepository;
    private UserJpaRepository userJpaRepository;

    @Autowired
    public SystemServiceImpl(RoleJpaRepository roleJpaRepository,
                             UserJpaRepository userJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
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
