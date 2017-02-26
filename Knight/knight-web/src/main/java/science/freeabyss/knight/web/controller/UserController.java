package science.freeabyss.knight.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import science.freeabyss.knight.model.UserEntity;
import science.freeabyss.knight.service.UserService;

/**
 * Created by abyss on 12/17/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryAll")
    public List<UserEntity> queryAll() {
        return userService.queryAll();
    }

    @RequestMapping("/login")
    public String login() {
        return "index";
    }
}

