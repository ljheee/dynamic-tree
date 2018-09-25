package com.me.ljheee.tree.aspect;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class WebLogAspect {

    private Logger log = Logger.getLogger(WebLogAspect.class);

    /**
     * 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
     * 填写的是要拦截的controller所在的包，拦截该包下的所有类的所有方法。
     */
    @Pointcut("execution(* com.me.ljheee.tree.module.*.controller..*(..))")
    public void aspect(){}


    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * @param point 切入点
     */
    @Before(value = "aspect()")
    public void before(JoinPoint point) {
        //获取方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        //访问路径
        List<Annotation> annotations = Arrays.asList(signature.getMethod().getDeclaredAnnotations())
                .stream()
                .filter(annotation -> annotation.annotationType() == RequestMapping.class)
                .collect(Collectors.toList());
        //如果没有RequestMapping注解，就赋值""
        String requestPath = annotations.size() >= 1
                ? signature.getMethod().getAnnotation(RequestMapping.class).value()[0] : "";
        //获取类名
        String className = point.getSignature().getDeclaringType().getSimpleName();
        //获取方法名
        String methodName = point.getSignature().getName();

        //输出请求路径，类名，方法名，参数列表
        log.log(Level.INFO, "\n---------Path: " + requestPath
                + "\n---------ClassName: " + className
                + "\n---------MethodName: " + methodName
                + "\n---------ParamList: {" + getArgs(point) + "}");

    }


    /**
     * 获取所有的参数名和值，组装为String
     * @param point
     * @return
     */
    private String getArgs(JoinPoint point) {
        String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            sb.append(parameterNames[i] + ":" + point.getArgs()[i].toString() + "; ");
        }
        return sb.toString();
    }



}
