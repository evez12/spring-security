package az.huseynov.springMVC.securityThymeleaf.dao;

import az.huseynov.springMVC.securityThymeleaf.enitity.User;


public interface UserDao {

    User findByUserName(String username);

    void save(User user);

}
