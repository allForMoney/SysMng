package com.resourcemng.controller;

import com.resourcemng.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Benjamin Winterberg
 */
@Controller
public class MainController  extends BaseController {

    @Autowired
    public MainController(ProjectService service) {
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) throws Exception {
        return "/loginpage.html";
    }
}
