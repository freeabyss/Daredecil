package science.freeabyss.template.spa.controller;

import science.freeabyss.template.spa.config.I18nPrefix;
import science.freeabyss.template.spa.model.dto.FunctionDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 视图
 *
 * @author abyss
 * @date 2019-01-22 17:09
 */
@Controller
public class ViewController {

    @GetMapping("/index")
    public String index(Model model) {
        List<FunctionDTO> functions = new ArrayList<>();
        FunctionDTO homeFunction = new FunctionDTO();
        homeFunction.setName("首页");
        homeFunction.setUrl("#home");
        homeFunction.setIcons("icon-home");
        functions.add(homeFunction);
        FunctionDTO authFunction = new FunctionDTO();
        authFunction.setName("权限管理");
        authFunction.setUrl("#auth");
        authFunction.setIcons("icon-home");
        functions.add(authFunction);
        model.addAttribute("functions", functions);

        return "index";
    }

    @I18nPrefix("home")
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @I18nPrefix("contact")
    @GetMapping("/contact")
    public String auth() {
        return "contact";
    }

    @GetMapping("/feature")
    public String feature() {
        return "feature";
    }
}
