package ru.sapronov.springsecurity.dao;

import org.springframework.stereotype.Repository;
import ru.sapronov.springsecurity.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ivan Sapronov
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleById(long id) {
        return entityManager.createQuery("select r from Role r where id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Role getRoleByName(String role) {
        return entityManager.createQuery("select r from Role r where role = :role", Role.class)
                .setParameter("role", role).getResultList().get(0);
    }

    @Override
    public void deleteRole(long id) {
        entityManager.remove(getRoleById(id));
    }
}
