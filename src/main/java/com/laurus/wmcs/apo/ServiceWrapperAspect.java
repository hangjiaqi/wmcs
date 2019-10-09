package com.laurus.wmcs.apo;


import com.laurus.wmcs.context.AopContext;
import com.laurus.wmcs.result.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * wrapper api impls with specified service bean
 *
 * @author wuzimei
 */
@Component
@Aspect
@Order(Integer.MAX_VALUE)
public class ServiceWrapperAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut(value = "@annotation(com.laurus.wmcs.apo.ServiceWrapper)")
    public void access() {

    }

    @Before("access()")
    public void before(JoinPoint joinPoint) throws Throwable {
        // 获取方法参数中的参数名
        String[] names = getParameterNames(joinPoint);
        // 获取方法中的参数
        Object[] args = joinPoint.getArgs();
        // 获取所有参数的类型
        Class<?>[] parameterClasses = getParameterClasses(joinPoint);
        // 判断aopContext中是否含有报错message
        if (AopContext.getCurrentReturn() != null) {
            return;
        }
        // 获取调用的函数名和参数，以及用什么服务处理
        // 从容器中获取指定的服务类对象， 如果没有找到对应的bean的话，会抛出错误，属于代码BUG
        Object bean = applicationContext.getBean(getServiceName(joinPoint));
        // 准备使用反射机制调用服务的方法
        // 获取实际服务的类
        Class<?> cls = bean.getClass();

        String methodName = getMethodName(joinPoint);
        // 获取实际服务的方法
        Method m = cls.getMethod(methodName, parameterClasses);

        // 调用方法
        Object x = m.invoke(bean, args);
        // 将返回结果保存到线程安全的上下文中
        AopContext.setCurrentReturn((Result) x);

    }


    @After("access()")
    public void after() {
        AopContext.clear();
    }

    private Class<?>[] getParameterClasses(JoinPoint joinPoint) {
        Parameter[] parameters = getParameters(joinPoint);
        Class<?>[] parameterClasses = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterClasses[i] = parameters[i].getType();
        }
        return parameterClasses;
    }

    private Parameter[] getParameters(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        return parameters;
    }

    private String[] getParameterNames(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] names = signature.getParameterNames();
        return names;
    }

    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        return methodName;
    }

    private String getServiceName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ServiceWrapper serviceWrapper = method.getAnnotation(ServiceWrapper.class);
        String value = serviceWrapper.value();
        return value;
    }


}
