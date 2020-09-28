package com.main.app.controller;

import com.main.app.entity.Todo;
import com.main.app.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    TodoRepo todoRepo;

    @GetMapping("/")
    public ModelAndView indexPage(ModelAndView model_view){
        List<Todo> todo = todoRepo.findAll();
        model_view.addObject("todo_list",todo);
        model_view.setViewName("index");
        return model_view;
    }

    @GetMapping("/showForm")
    public ModelAndView showForm(@RequestParam(value = "list_id",required = false) Integer id,
                                 ModelAndView model_view){
       Optional<Todo> todo = Optional.of(new Todo());
       if (id != null){
           todo = todoRepo.findById(id);
       }
       model_view.addObject("todo",todo);
       model_view.setViewName("form/todo_form");
       return model_view;
    }

    @PostMapping("/saveOrUpdate")
    public ModelAndView saveOrUpdate(@ModelAttribute("todo") @Valid Todo todo,
                                     ModelAndView model_view,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors())
            model_view.setViewName("form/todo_form");
        else{
        todoRepo.save(todo);
        model_view.setViewName("redirect:/");
        }
        return model_view;
    }

    @GetMapping("/deleteTodo")
    public ModelAndView deleteTodo(@RequestParam("list_id") int id,
                                   ModelAndView model_view){
        todoRepo.deleteById(id);
        model_view.setViewName("redirect:/");
        return model_view;
    }

}
