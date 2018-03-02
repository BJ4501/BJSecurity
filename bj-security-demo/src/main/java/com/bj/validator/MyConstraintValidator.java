package com.bj.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by neko on 2018/3/2.
 */
//ConstraintValidator<A,T> A:验证的注解是哪个？ T:需要验证的东西类型
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object>{

    //在校验器中，可以注入Spring容器中的任何元素
    //@Autowired
    //private UserService userService;

    //当校验器初始化
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("init");
    }

    //校验逻辑
    //true:校验成功 false:校验失败
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(value);
        return false;
    }

}
