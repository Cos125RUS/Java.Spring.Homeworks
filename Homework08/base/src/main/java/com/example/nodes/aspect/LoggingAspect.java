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
        ResponseEntity response = (ResponseEntity) joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("LoggingAspect\n\u001B[33mВходящий запрос типа: \u001B[35m'" +
                joinPoint.getSignature().getName() + "'\u001B[33m\n" +
                "Переданные на сервер данные: \u001B[35m" + Arrays.toString(joinPoint.getArgs()) +
                "\u001B[33m\nСтатус ответа: \u001B[35m" + response.getStatusCode() +
                "\u001B[33m\nВозвращённые сервером данные: \u001B[35m" +
                response.getBody() + "\u001B[33m\nВремя обработки запроса: \u001B[35m" +
                executionTime + "мс\t\u001B[33m(Ну, то бишь капец как медленно!)\u001B[0m");
        return response;
    }
}
