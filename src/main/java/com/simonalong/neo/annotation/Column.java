package com.simonalong.neo.annotation;

import static com.simonalong.neo.NeoConstant.DEFAULT_TABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouzhenyong
 * @since 2019/9/3 上午12:08
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    /**
     * 表中的列名
     * @return 表的列名
     */
    String value() default "";

    /**
     * 表的别名
     * @return 对应表的别名
     */
    String table() default DEFAULT_TABLE;
}