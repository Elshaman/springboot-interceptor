package com.brude.springboot.app.interceptor.springbootinterceptor.interceptors;

import java.util.logging.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    //creando un logger
    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
              logger.info("LoadingTimeInterceptor: postHandle() saliendo" + ((HandlerMethod) handler).getMethod().getName());      
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                HandlerMethod metodo = ((HandlerMethod)handler);
                logger.info("LoadingTimeInterceptor: preHandle() called...."+ metodo.getMethod().getName());
                return true;
    }


}
