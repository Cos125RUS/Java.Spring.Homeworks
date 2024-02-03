package com.example.nodes.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@Aspect
@Log
public class LoggingAspect {

    @Around(value = "@annotation(TrackUserAction)")
    public ResponseEntity logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("Входящий запрос '" + joinPoint.getSignature().getName() + "'\n" +
                "Аргументы: " + Arrays.toString(joinPoint.getArgs()));

        ResponseEntity response = (ResponseEntity) joinPoint.proceed();


        long executionTime = System.currentTimeMillis() - start;
        log.info("Статус ответа: " + response.getStatusCode() +
                "\nВремя обработки запроса: " + executionTime +
                "мс\t(Ну, то бишь капец как медленно!)");

        return response;
    }
}
