package science.freeabyss.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author abyss
 * @date 2018/5/2
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/admin")
public class HomeController {


    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}

