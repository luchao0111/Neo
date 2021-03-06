package com.simonalong.neo.sql.builder;

import com.simonalong.neo.NeoMap;
import lombok.experimental.UtilityClass;

/**
 * @author shizi
 * @since 2020/3/22 下午7:51
 */
@UtilityClass
public class DeleteSqlBuilder {

    public String build(String tableName, NeoMap valueMap) {
        return "delete from " + tableName + SqlBuilder.buildWhere(valueMap);
    }
}
