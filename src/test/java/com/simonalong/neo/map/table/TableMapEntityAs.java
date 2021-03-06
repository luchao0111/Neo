package com.simonalong.neo.map.table;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shizi
 * @since 2020/3/22 下午5:49
 */
@Data
@Accessors(chain = true)
public class TableMapEntityAs {

    private String name;
    private Integer age;
    private String userName;
}
