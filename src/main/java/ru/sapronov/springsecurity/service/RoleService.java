package ru.sapronov.springsecurity.service;

import ru.sapronov.springsecurity.models.Role;

/**
 * @author Ivan Sapronov
 */
public interface RoleService {
    void deleteRole(long id);

    Role getRoleByName(String role);
}
