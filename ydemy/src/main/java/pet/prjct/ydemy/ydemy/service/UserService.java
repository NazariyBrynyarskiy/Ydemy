package pet.prjct.ydemy.ydemy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.prjct.ydemy.ydemy.dao.Crud;
import pet.prjct.ydemy.ydemy.dao.impl.RoleImpl;
import pet.prjct.ydemy.ydemy.model.UserLogin;
import pet.prjct.ydemy.ydemy.model.entity.Authority;
import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private Crud<User, String> userCrudImpl;
    private RoleImpl roleImpl;
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(Crud<User, String> userCrudImpl,
                       RoleImpl roleImpl,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userCrudImpl = userCrudImpl;
        this.roleImpl = roleImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userCrudImpl.findById(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(user.getAuthority().getAuthority()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), list);
    }

    @Transactional
    public void save(UserLogin userLogin) {
        User user = new User();
        Authority authority = new Authority();

        user.setUsername(userLogin.getUsername());
        user.setName(userLogin.getName());
        user.setSurname(userLogin.getSurname());
        user.setEmail(userLogin.getEmail());
        user.setPassword("{bcrypt}" + passwordEncoder.encode(userLogin.getPassword()));
        user.setEnabled(1);

        authority.setUsername(user.getUsername());
        authority.setAuthority("ROLE_USER");

        userCrudImpl.save(user);
        roleImpl.save(authority);
    }

    public boolean existsByUsername(String username) {
        return userCrudImpl.existsById(username);
    }

    public boolean existsByEmail(String email) {
        return userCrudImpl.existsByParameter(email);
    }

}
