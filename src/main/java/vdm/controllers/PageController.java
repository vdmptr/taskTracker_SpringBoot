package vdm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/page")
public class PageController {

    @RequestMapping(value = "/task/{id}")
    public String getCommentOfTask(@PathVariable int id, HttpSession session){
        session.setAttribute("taskId", id);
        return "comment";
     }
}
