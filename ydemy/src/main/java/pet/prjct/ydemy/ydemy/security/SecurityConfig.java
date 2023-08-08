package pet.prjct.ydemy.ydemy.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pet.prjct.ydemy.ydemy.service.UserService;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configer ->
                    configer
                            .requestMatchers("").hasRole("USER")
                            .requestMatchers("/leaders/**").hasAnyRole("MANAGER", "ADMIN")
                            .requestMatchers("/systems/**").hasRole("ADMIN")
                            .requestMatchers("/register/**").permitAll()
                            .anyRequest().authenticated()
                )
                .exceptionHandling(configer ->
                        configer
                                .accessDeniedPage("/invalid-page")
                )
                .formLogin(form ->
                        form
                                .loginPage("/auth")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                );
        return http.build();
    }
}

