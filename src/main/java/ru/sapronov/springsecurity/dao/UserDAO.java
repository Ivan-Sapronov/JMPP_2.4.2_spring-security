package ru.sapronov.springsecurity.dao;

import ru.sapronov.springsecurity.models.User;

import java.util.List;

/**
 * @author Ivan Sapronov
 */
public interface UserDAO {
    List<User> index();

    User show(long id);

    User getUserByUsername(String username);

    void save(User user);

    void update(long id, User user);

    void delete(long id);
}
