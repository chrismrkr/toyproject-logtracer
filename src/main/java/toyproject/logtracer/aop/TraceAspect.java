package toyproject.logtracer.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TraceAspect {
    private ThreadLocal<Integer> levelStorage = new ThreadLocal<>();
    @Around("@annotation(toyproject.logtracer.annotation.Trace)")
    public Object doTraceLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("[trace] {} {} starts.", getStartLog(), getMethodName(joinPoint.getSignature()));
        try {
            Object proceed = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            log.info("[trace] {} {} ends. times={}ms ", getEndLog(), getMethodName(joinPoint.getSignature()), endTime-startTime);
            return proceed;
        } catch(IllegalStateException e) {
            long endTime = System.currentTimeMillis();
            log.info("[trace] X{} {} fails. times={}ms ", getEndLog(), getMethodName(joinPoint.getSignature()), endTime-startTime);
            throw e;
        }
    }

    private String getEndLog() {
        Integer level = levelStorage.get();
        if(level == 1) {
            levelStorage.remove();
            return "<==";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<==");
        for(int i=0; i<level-1; i++) {
            builder.append("|==");
        }
        levelStorage.set(level-1);
        return builder.toString();
    }

    private String getStartLog() {
        Integer level = levelStorage.get();
        if(level == null) {
            levelStorage.set(1);
            return "==>";
        }
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<level; i++) {
            builder.append("==|");
        }
        levelStorage.set(level+1);
        return builder.append("==>").toString();
    }
    private String getMethodName(Signature signature) {
        return signature.getName();
    }
}