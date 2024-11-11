package az.huseynov.springMVC.securityThymeleaf.dao;

import az.huseynov.springMVC.securityThymeleaf.enitity.Role;

public interface RoleDao {
    Role findRoleByName(String name);
}
