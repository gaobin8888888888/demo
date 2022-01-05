package com.learn.demo.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gb
 * @date 2021/3/15 6:07 下午
 * @description
 */
@Aspect
@Component
public class OnMetricsDeal {

    @Pointcut("@annotation(com.learn.demo.test.aop.OnMetrics)")
    public void aspect(){}

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Class<?> targetClass = point.getTarget().getClass();
        Signature signature = point.getSignature();
        String name = signature.getName();
        Class[] parameterTypes = ((MethodSignature) signature).getParameterTypes();
        Method declaredMethod = targetClass.getDeclaredMethod(name, parameterTypes);
        System.out.println(declaredMethod.toString());

        if (declaredMethod != null) {
            RequestMapping onMetrics = declaredMethod.getAnnotation(RequestMapping.class);
            if (onMetrics != null) {
                System.out.println(Arrays.toString(point.getArgs()));
//                System.out.println("count:" + onMetrics.count());
//                System.out.println("timer:" + onMetrics.timer());
                System.out.println("name:" + onMetrics.name());
                String[] value = onMetrics.value();
                System.out.println("name:" + Arrays.toString(value));

                //获取参数名称
                LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
                String[] params = paramNames.getParameterNames(declaredMethod);

                Object[] args = point.getArgs();

                Map<String, Object> reqsParams = new HashMap<>();
                if (params != null){
                    for (int i = 0; i < params.length; i++){
                        reqsParams.put(params[i], args[i] == null ? "" : args[i]);
                    }
                }

                Object proceed = point.proceed();
                System.out.println(proceed);
            }
        }
        return null;
    }

    @AfterThrowing(value = "aspect()", throwing = "throwable")
    public void afterThrowing(Throwable throwable){
        System.out.println(111111);
        System.out.println(throwable.getMessage());
        throwable.printStackTrace();
    }
}
