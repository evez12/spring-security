package az.huseynov.springMVC.securityThymeleaf.service;


import az.huseynov.springMVC.securityThymeleaf.enitity.User;
import az.huseynov.springMVC.securityThymeleaf.model.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String username);

	void save(WebUser webUser);

}
