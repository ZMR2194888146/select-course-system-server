package student.course.scsv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.course.scsv.service.LoginService;
import student.course.scsv.service.UserService;

@RestController
@RequestMapping(path = "/api")
public class APIController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public String login(Long id, String password, String usertype){
       return loginService.checkLogin(id, password,usertype);
    }

    @GetMapping( value = "/user/{id}")
    public String getInfo(@PathVariable("id") Long id){
        return userService.getUserInfo(id);
    }
}
