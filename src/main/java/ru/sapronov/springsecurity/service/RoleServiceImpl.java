package ru.sapronov.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sapronov.springsecurity.dao.RoleDao;

/**
 * @author Ivan Sapronov
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void deleteRole(long id) {
        roleDao.deleteRole(id);
    }
}
