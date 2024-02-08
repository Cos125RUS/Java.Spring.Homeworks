package com.example.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

/**
 * Аспектное логирование
 */
@Aspect
@Log
public class TransferLogging {
    /**
     * Логирование новых транзакций
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.example.services.TransferService.transferMoney(..))")
    public Object addLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        long start = System.currentTimeMillis();
        boolean result = (boolean) joinPoint.proceed();
        log.info("Новая транзакция (ID Отправитель: " + args[0] +
                ", ID Получателя: " + args[1] + ", Сумма перевода: " + args[2] +
                ", Статус обработки транзакции: " + result +
                ", Время обработки: " + (System.currentTimeMillis() - start) + "мс)");
        return result;
    }
}
