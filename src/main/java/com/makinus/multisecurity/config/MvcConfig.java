package com.makinus.multisecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by abuabdul on 2020-04-23
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("landing");
        registry.addViewController("/regular/home").setViewName("regular/home");
        registry.addViewController("/special/home").setViewName("special/home");
        registry.addViewController("/regular/login").setViewName("regular/login");
        registry.addViewController("/special/login").setViewName("special/login");
    }
}
