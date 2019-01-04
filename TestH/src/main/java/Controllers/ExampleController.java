package Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class ExampleController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name, Map<String,Object> model) {
        model.put("name",name);
        return "Hello" + name;
    }
}
