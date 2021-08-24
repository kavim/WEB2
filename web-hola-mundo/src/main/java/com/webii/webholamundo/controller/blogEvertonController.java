package com.webii.webholamundo.controller;

import com.webii.webholamundo.model.Postagens;
import com.webii.webholamundo.repository.web2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class blogEvertonController {
    @Autowired
    web2Repository blogEvertonRepository;

    @RequestMapping(value = "/postagens", method = RequestMethod.GET)
    public ModelAndView getPostagens()
    {
        ModelAndView mv = new ModelAndView("postagens");
        List<Postagens> postagens = blogEvertonRepository.findAll();
        mv.addObject("postagens", postagens);

        return mv;
    }

//    @RequestMapping("/error")
//    public ModelAndView errorPage(){
//        ModelAndView mv = new ModelAndView("error404");
//        return mv;
//    }
}
