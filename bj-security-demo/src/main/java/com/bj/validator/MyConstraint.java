package com.bj.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 样例注解
 * Created by neko on 2018/3/2.
 */
@Target({ElementType.METHOD,ElementType.FIELD})//该注解可以标记在什么位置 (方法与字段)
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Constraint(validatedBy = MyConstraintValidator.class)//使用什么类进行的校验
public @interface MyConstraint {

    //当校验不通过时默认发的消息
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
