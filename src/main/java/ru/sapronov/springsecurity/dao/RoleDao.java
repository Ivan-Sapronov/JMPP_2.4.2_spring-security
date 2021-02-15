package ru.sapronov.springsecurity.dao;

import ru.sapronov.springsecurity.models.Role;

/**
 * @author Ivan Sapronov
 */
public interface RoleDao {

    Role getRoleById(long id);

    Role getRoleByName(String role);

    void deleteRole(long id);
}
