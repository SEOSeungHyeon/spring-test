package com.example.springtest.config;

import com.example.springtest.filter.RequestBodyXssFilter;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    @Order(1)
    public FilterRegistrationBean<MultipartFilter> filterRegistrationBean() {
        Map<String, String> initParam = new HashMap<>();
        initParam.put("multipartResolverBeanName", "multipartResolver");

        FilterRegistrationBean<MultipartFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new MultipartFilter());
        filterRegistration.setOrder(1);
        filterRegistration.setInitParameters(initParam);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Bean
    @Order(2)
    public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean2() {
        FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new XssEscapeServletFilter());
        filterRegistration.setOrder(2);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Bean
    @Order(3)
    public FilterRegistrationBean<RequestBodyXssFilter> filterRegistrationBean3() {
        FilterRegistrationBean<RequestBodyXssFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new RequestBodyXssFilter());
        filterRegistration.setOrder(3);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.addInitParameter("extUrls", "/linkUrl1,/linkUrl2,/linkUrl3");
        return filterRegistration;
    }
}
