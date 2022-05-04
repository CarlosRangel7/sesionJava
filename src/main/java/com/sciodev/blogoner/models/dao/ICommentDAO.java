package com.sciodev.blogoner.models.dao;

import com.sciodev.blogoner.models.entity.Comment;
import java.util.List;

public interface ICommentDAO {
    public List<Comment> findAll();

    public void save(Comment comment);

    public Comment findOne(Long id);

    public void delete(Long id);
}