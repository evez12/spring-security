package az.huseynov.springMVC.securityThymeleaf.dao;

import az.huseynov.springMVC.securityThymeleaf.enitity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {
    EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public User findByUserName(String username) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE username=:uName and enabled=true", User.class);
        query.setParameter("uName", username);
        User theUser = null;
        try {
            theUser = query.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }

}
