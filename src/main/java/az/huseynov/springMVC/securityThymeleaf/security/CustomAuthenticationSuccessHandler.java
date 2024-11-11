package az.huseynov.springMVC.securityThymeleaf.security;

import az.huseynov.springMVC.securityThymeleaf.enitity.User;
import az.huseynov.springMVC.securityThymeleaf.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        System.out.print("in onAuthenticationSuccess -> ");

        String username = authentication.getName();

        System.out.println("username: " + username);

        User theUser = userService.findByUserName(username);

        HttpSession session = request.getSession();
        session.setAttribute("user", theUser);

        // forward to home page
        response.sendRedirect(request.getContextPath()+"/");

    }
}
