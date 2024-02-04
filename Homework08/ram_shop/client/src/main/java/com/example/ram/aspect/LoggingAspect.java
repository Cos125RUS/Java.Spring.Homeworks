package com.example.ram.aspect;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Log
public class LoggingAspect {

    @AfterReturning(value = "@annotation(Logging)")
    public void addLog(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        log.info("\nНовый запрос просмотра!\tID Пользователя: " + args[0] + "\tЭпизод: " + args[1]);
    }
}
