package com.example.springtest.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class RequestBodyXssFilter implements Filter {
    private List<String> extUrl;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        RequestWrapper reqWrapper = null;
        String path = ((HttpServletRequest) req).getServletPath();

        try {
            if (!extUrl.contains(path)) {

                reqWrapper  = new RequestWrapper(request);
                chain.doFilter(reqWrapper , response);
            } else {
                chain.doFilter(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludePattern = filterConfig.getInitParameter("extUrls");
        extUrl = Arrays.asList(excludePattern.split(","));
    }

    @Override
    public void destroy() {
    }
}
         
