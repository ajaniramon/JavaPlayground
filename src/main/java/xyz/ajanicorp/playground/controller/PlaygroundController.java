package xyz.ajanicorp.playground.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xyz.ajanicorp.playground.configuration.ResourceValueProvider;

import javax.swing.text.View;

/**
 * Created by Ajani on 04/11/2017.
 */
@Controller
public class PlaygroundController {


    @Autowired
    ResourceValueProvider resourceValueProvider;

    @GetMapping("/")
    public ModelAndView renderHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("logo_href",resourceValueProvider.getApiLogoUrl());
        return modelAndView;
    }
}
