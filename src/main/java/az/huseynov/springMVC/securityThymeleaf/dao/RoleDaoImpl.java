package az.huseynov.springMVC.securityThymeleaf.dao;

import az.huseynov.springMVC.securityThymeleaf.enitity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String name) {

        TypedQuery<Role> query = entityManager.createQuery("FROM Role WHERE name=:roleName", Role.class);
        query.setParameter("roleName", name);

        Role theRole = null;

        try {
            theRole = query.getSingleResult();

        } catch (Exception e) {
            theRole = null;
        }

        return theRole;
    }
}
