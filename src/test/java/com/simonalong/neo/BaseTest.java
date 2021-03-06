package com.simonalong.neo;

import com.alibaba.fastjson.JSON;
import java.util.Optional;

/**
 * @author zhouzhenyong
 * @since 2019/3/16 上午11:41
 */
public class BaseTest {

    public void show(Object object) {
        if(null == object){
            show("obj is null ");
            return;
        }
        Optional.of(object).ifPresent(objects1 -> System.out.println(objects1.toString()));
    }

    public void sleep(Integer seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
