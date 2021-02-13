package ru.sapronov.springsecurity.dao;

import org.springframework.stereotype.Repository;
import ru.sapronov.springsecurity.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Ivan Sapronov
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index(){
        List<User> users = entityManager.createQuery("SELECT u from User u").getResultList();
        return users;
    }

    @Override
    public User show(long id){
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select u from User u where username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(long id, User user) {
        User userInDB = entityManager.find(User.class, id);
        userInDB.setUsername(user.getUsername());
        userInDB.setFirstname(user.getFirstname());
        userInDB.setSurname(user.getSurname());
        userInDB.setAge(user.getAge());
        userInDB.setEmail(user.getEmail());
        userInDB.setRoles(user.getRoles());
    }

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
