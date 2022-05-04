package com.sciodev.blogoner.models.dao;

import com.sciodev.blogoner.models.entity.User;
import java.util.List;

public interface IUserDAO {
    public List<User> findAll();

    public void save(User user);

    public User findOne(Long id);

    public void delete(Long id);
}
