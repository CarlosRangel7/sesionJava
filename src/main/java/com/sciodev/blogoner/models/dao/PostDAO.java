package com.sciodev.blogoner.models.dao;

import com.sciodev.blogoner.models.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("PostDAOJPA")
public class PostDAO implements IPostDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("from Post").getResultList();
    }


    @Transactional
    @Override
    public void save(Post post) {
        if(post.getId() != null && post.getId() >0 ){
            entityManager.merge(post);
        }else{
            entityManager.persist(post);
        }
    }

    @Override
    public Post findOne(Long id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public void delete(Long id) {


    }
}
