package com.brude.springboot.app.interceptor.springbootinterceptor.interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

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
              long end = System.currentTimeMillis();
              long start = (long) request.getAttribute("start");
              long result = end - start;
              logger.info("Tiempo transcurrido: " + result + " milisegundos");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                HandlerMethod metodo = ((HandlerMethod)handler);
                logger.info("LoadingTimeInterceptor: preHandle() called...."+ metodo.getMethod().getName());
                long start = System.currentTimeMillis();
                request.setAttribute("start", start);
                Random random = new Random();
                int delay = random.nextInt(500);
                Thread.sleep(delay);

                //en caso de retornar false: 
                Map<String,String> resultado = new HashMap<String,String>();
                resultado.put("error", "no tiene acceso a la pagina");
                resultado.put("date" , new Date().toString());

                ObjectMapper maper = new ObjectMapper();
                String jsoString = maper.writeValueAsString(resultado);
                response.setContentType("application/json");
                response.setStatus(401);
                response.getWriter().write(jsoString);

                return false;
               // return true;
    }


}
