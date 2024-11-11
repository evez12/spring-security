package az.huseynov.springMVC.securityThymeleaf.security;

import az.huseynov.springMVC.securityThymeleaf.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

//
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//
//        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        theUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, active FROM user WHERE username=?");
//
//        theUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM roles WHERE username=?");
//
//        return theUserDetailsManager;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEnCoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEnCoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {
        http.authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/view/**").permitAll()
                                .requestMatchers("/register/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser") // login.html  form action=/authenticateTheUser (spring do automatically check)
                                .failureUrl("/login?error")
                                .successHandler(customAuthenticationSuccessHandler)
                                .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
                );

        return http.build();
    }

}
