package com.simonalong.neo.sql.builder;

import com.simonalong.neo.Columns;
import com.simonalong.neo.Neo;
import com.simonalong.neo.NeoMap;
import lombok.experimental.UtilityClass;

/**
 * @author shizi
 * @since 2020/3/22 下午8:19
 */
@UtilityClass
public class SelectSqlBuilder {

    /**
     * 拼接 select one，多列单值
     *
     * @param neo       数据库对象
     * @param tableName 表名
     * @param columns   select后面的列名，其中值为db中的列名，如果为空，则会将tableName中的所有列展示出来
     * @param searchMap 搜索条件
     * @return 拼接字段：select `group`, `user_name` from  where `id` =  ? and `name` =  ? limit 1
     */
    public String buildOne(Neo neo, String tableName, Columns columns, NeoMap searchMap) {
        return buildList(neo, tableName, columns, searchMap) + limitOne();
    }

    /**
     * 拼接 select list 多列多值
     *
     * @param neo       数据库对象
     * @param tableName 表名
     * @param columns   select后面的列名，其中值为db中的列名，如果为空，则会将tableName中的所有列展示出来
     * @param searchMap 搜索条件
     * @return 拼接字段：select `group`, `user_name` from  where `id` =  ? and `name` =  ?
     */
    public String buildList(Neo neo, String tableName, Columns columns, NeoMap searchMap) {
        return "select " + buildColumns(neo, tableName, columns) + " from " + tableName + SqlBuilder.buildWhere(searchMap) + SqlBuilder.buildOrderBy(searchMap);
    }

    /**
     * 拼接 select value 单列单值
     *
     * @param tableName 表名
     * @param field     某个列
     * @param searchMap 搜索条件
     * @return 拼接字段：select `group` from neo_table1 where `id` =  ? and `name` =  ? limit 1
     */
    public String buildValue(String tableName, String field, NeoMap searchMap) {
        return buildValues(tableName, field, searchMap) + limitOne();
    }

    /**
     * 拼接 select values 单列多值
     *
     * @param tableName 表名
     * @param field     某个列
     * @param searchMap 搜索条件
     * @return 拼接字段：select `group` from neo_table1 where `id` =  ? and `name` =  ?
     */
    public String buildValues(String tableName, String field, NeoMap searchMap) {
        return "select " + SqlBuilder.toDbField(field) + " from " + tableName + SqlBuilder.buildWhere(searchMap) + SqlBuilder.buildOrderBy(searchMap);
    }

    /**
     * 拼接 select page，多列多值，按照分页
     *
     * @param neo        数据库
     * @param tableName  表名
     * @param columns    多个列
     * @param searchMap  搜索条件
     * @param startIndex 页面位置
     * @param pageSize   页面大小
     * @return 拼接字段：select `group` from  where `id` =  ? and `name` =  ? limit 3, 20
     */
    public String buildPage(Neo neo, String tableName, Columns columns, NeoMap searchMap, Integer startIndex, Integer pageSize) {
        return buildList(neo, tableName, columns, searchMap) + " limit " + startIndex + ", " + pageSize;
    }

    /**
     * 拼接 select count， 单列单值，整数
     *
     * @param tableName 表名
     * @param searchMap 搜索条件
     * @return 拼接字段：select count(1) from neo_table1 where `id` =  ? and `name` =  ? limit 1
     */
    public String buildCount(String tableName, NeoMap searchMap) {
        return "select count(1) from " + tableName + SqlBuilder.buildWhere(searchMap) + limitOne();
    }

    /**
     * 拼接 select 后面的选项
     *
     * @param neo       数据库对象
     * @param tableName 表名
     * @param columns   列名
     * @return 拼接字段：`group`, `name`
     */
    public String buildColumns(Neo neo, String tableName, Columns columns) {
        if (!Columns.isEmpty(columns)) {
            return columns.toSelectString();
        } else {
            return Columns.of().setNeo(neo).table(tableName).toSelectString();
        }
    }

    private String limitOne() {
        return " limit 1";
    }
}
