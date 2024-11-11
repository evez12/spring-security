package az.huseynov.springMVC.securityThymeleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        theUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, active FROM users WHERE username=?");

        theUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM roles WHERE username=?");

        return theUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .anyRequest().permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser") // login.html  form action=/authenticateTheUser (spring do automatically check)
                                .failureUrl("/login?error")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

}
