package com.sciodev.blogoner.models.dao;

import com.sciodev.blogoner.models.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("UserDAOJPA")
public class UserDAO implements IUserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }


    @Transactional
    @Override
    public void save(User user) {
        if(user.getId() != null && user.getId() >0 ){
            entityManager.merge(user);
        }else{
            entityManager.persist(user);
        }
    }

    @Override
    public User findOne(Long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void delete(Long id) {

    }
}
