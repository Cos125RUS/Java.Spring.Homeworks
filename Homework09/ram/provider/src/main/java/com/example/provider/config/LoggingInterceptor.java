package com.example.provider.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Перехватчик
 */
@NoArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {
    private long start;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        start = System.currentTimeMillis();
        System.out.println("=======================================");
        System.out.println("Remote Address: " + request.getRemoteAddr());
        System.out.println("Request URI: " + request.getRequestURI().replace("/", ""));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        System.out.println("Response status: " + response.getStatus());
        System.out.println("Request processing time: " +
                (System.currentTimeMillis() - start) + "mc");
        System.out.println("=======================================");
    }

}
