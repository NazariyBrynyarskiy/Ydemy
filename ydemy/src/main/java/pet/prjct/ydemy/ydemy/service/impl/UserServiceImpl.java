package pet.prjct.ydemy.ydemy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.prjct.ydemy.ydemy.dao.RoleRepository;
import pet.prjct.ydemy.ydemy.dao.UserRepository;
import pet.prjct.ydemy.ydemy.model.UserLogin;
import pet.prjct.ydemy.ydemy.model.entity.Authority;
import pet.prjct.ydemy.ydemy.model.entity.User;
import pet.prjct.ydemy.ydemy.service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;



//    public static void main(String[] args) {
//        UserLogin userLogin = new UserLogin(
//                "usersam",
//                "Sam",
//                "Bam",
//                "bam@gmail.com",
//                "sam123"
//        );
//        sav(userLogin, new BCryptPasswordEncoder());
//
//    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(user.getAuthority().getAuthority()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), list);
    }

    @Override
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

        userRepository.save(user);
        roleRepository.save(authority);
    }

    @Override
    public boolean containsUserByUsername(String username) {
        return userRepository.containsUserByUsername(username);
    }

    @Override
    public boolean containsUserByEmail(String email) {
        return userRepository.containsUserByEmail(email);
    }

    //    @Deprecated
//    public static void sav(UserLogin userLogin, BCryptPasswordEncoder passwordEncoder) {
//        User user = new User();
//        Authority authority = new Authority();
//
//        user.setUsername(userLogin.getUsername());
//        user.setName(userLogin.getName());
//        user.setSurname(userLogin.getSurname());
//        user.setEmail(userLogin.getEmail());
//        user.setPassword("{bcrypt}" + passwordEncoder.encode(userLogin.getPassword()));
//        user.setEnabled(1);
//
//        authority.setUsername(user.getUsername());
//        authority.setAuthority("ROLE_USER");
//
//        try(Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/Ydemy", "springstudent", "springstudent")) {
//            System.out.println("UserLogin" + userLogin);
//            System.out.println("User" + user);
//            System.out.println("Authority" + authority);
//            try(PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO `users` VALUES(?, ?, ?, ?, ?, ?)")) {
//                statement.setString(1, user.getUsername());
//                statement.setString(2, user.getName());
//                statement.setString(3, user.getSurname());
//                statement.setString(4, user.getEmail());
//                statement.setString(5, user.getPassword());
//                statement.setInt(6, user.getEnabled());
//
//                statement.execute();
//            }
//            try(PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO `authorities` VALUES(?, ?)")) {
//                statement.setString(1, authority.getUsername());
//                statement.setString(2, authority.getAuthority());
//
//                statement.execute();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
