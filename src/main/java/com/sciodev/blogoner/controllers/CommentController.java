package com.sciodev.blogoner.controllers;

import com.sciodev.blogoner.models.dao.ICommentDAO;
import com.sciodev.blogoner.models.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    @Qualifier("CommentDAOJPA")
    private ICommentDAO commentDAO;

    @RequestMapping(value = "/list-comments", method = RequestMethod.GET)
    public String listPosts(Model model) {
        model.addAttribute("title", "Post List");
        model.addAttribute("comments", commentDAO.findAll());
        return "list-comments";
    }

    @RequestMapping(value = "/formC")
    public String create(Map<String, Object> model) {
        Comment comment = new Comment();
        model.put("comment", comment);
        model.put("title", "Comment Form");
        return "formC";
    }

    @RequestMapping(value = "/formC/{id}")
    public String edit(@PathVariable(value="id") Long id, Map<String, Object> model){
        if(id > 0){
            model.put("post", commentDAO.findOne(id));
            model.put("title", "Comment Edit");
        }else{
            return "redirect:list-comments";
        }
        return "formC";
    }

   /* @RequestMapping(value = "/deletePost/{id}")
    public String delete(@PathVariable(value="id") Long id,Map<String, Object> model){

        return "redirect:list-users";
    }*/

    @RequestMapping(value = "/formC", method = RequestMethod.POST)
    public String save(@Valid Comment comment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Comment Form");
            return "formC";
        }
        commentDAO.save(comment);
        return "redirect:list-comments";
    }
}
