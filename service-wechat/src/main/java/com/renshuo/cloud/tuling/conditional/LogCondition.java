package com.renshuo.cloud.tuling.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by Lenovo on 2021/3/19.
 */
public class LogCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if(conditionContext.getBeanFactory().containsBean("logAspect")){
            return true;

        }
        return false;
    }
}
