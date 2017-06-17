package com.resourcemng.controller;

import com.resourcemng.service.UserService;
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
public class MainController {

    @Autowired
    public MainController(UserService service) {
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) throws Exception {
        return "index";
    }
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    @ResponseBody
    public String login(Map<String, Object> model) throws Exception {
        return "success";
    }
}
