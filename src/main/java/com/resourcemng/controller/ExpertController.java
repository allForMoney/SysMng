package com.resourcemng.controller;

import com.resourcemng.service.ExpertServiceService;
import com.resourcemng.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Benjamin Winterberg
 */
@Controller
public class ExpertController {

  ExpertServiceService service;

    @RequestMapping("/AAA")
    public String index(Map<String, Object> model) throws Exception {
        return "/loginpage.html";
    }
}
