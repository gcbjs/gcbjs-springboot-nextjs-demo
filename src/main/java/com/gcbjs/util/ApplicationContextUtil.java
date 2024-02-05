package com.gcbjs.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationContextUtil
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/5 14:04
 * @Version 1.0
 **/
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return context;
    }


    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException{
        return getApplicationContext().getBean(requiredType);
    }
}
