package com.baizhi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {
    @Autowired
    private RedisTemplate redisTemplate;


    @Around("execution(* com.baizhi.service.*Impl.query*(..))")
    public Object addCache(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println("环绕通知");
        //Object obj = joinPoint.proceed();
        //System.out.println(obj);
        //获取类的全路径
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println(name);

        //获取方法名
        String name1 = joinPoint.getSignature().getName();
        System.out.println(name1);
        stringBuilder.append(name).append(name1);
        //实参值
        Object[] args = joinPoint.getArgs();
        for (Object arg:args){
            System.out.println(arg);
            stringBuilder.append(arg);
        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
       // valueOperations.set(stringBuilder.toString() ,obj);
        Object obj =null;
        if (redisTemplate.hasKey(stringBuilder.toString())){
             obj = valueOperations.get(stringBuilder.toString());
        }else {
            //没有key
             obj =null;//放行请求
            try{
                obj=joinPoint.proceed();
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
            System.out.println(obj);
            //valueOperations.set(stringBuilder.toString(),obj);
        }

        return obj;
    }

    @After("within(com.baizhi.service.*Impl) && !execution(* com.baizhi.service.*Impl.query*(..))")
    public void delCache(){

    }
}
