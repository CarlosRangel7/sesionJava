package com.sciodev.blogoner.controllers;

import com.sciodev.blogoner.models.dao.IUserDAO;
import com.sciodev.blogoner.models.entity.User;
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
public class UserController {
    @Autowired
    @Qualifier("UserDAOJPA")
    private IUserDAO userDAO;

    @RequestMapping(value = "/list-users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("title", "Users List");
        model.addAttribute("users", userDAO.findAll());
        return "list-users";
    }

    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        model.put("title", "User Form");
        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String edit(@PathVariable(value="id") Long id, Map<String, Object> model){
        if(id > 0){
            model.put("user", userDAO.findOne(id));
            model.put("title", "User Edit");
        }else{
            return "redirect:list-users";
        }
        return "form";
    }

   /* @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value="id") Long id,Map<String, Object> model){

        return "redirect:list-users";
    }*/

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "User Form");
            return "form";
        }
        userDAO.save(user);
        return "redirect:list-users";
    }
}
