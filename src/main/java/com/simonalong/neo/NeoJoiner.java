package com.simonalong.neo;

import com.simonalong.neo.core.join.AbstractBaseJoinner;
import com.simonalong.neo.db.TableJoinOn;
import com.simonalong.neo.db.NeoPage;
import com.simonalong.neo.sql.builder.JoinSqlBuilder;

import java.util.List;

/**
 * @author shizi
 * @since 2020/5/23 6:39 PM
 */
public class NeoJoiner extends AbstractBaseJoinner {

    private Neo neo;

    public NeoJoiner(Neo neo) {
        this.neo = neo;
    }

    @Override
    public TableMap one(Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeOne(JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public <T> T one(Class<T> tClass, Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeOne(tClass, JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public List<TableMap> list(Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeList(JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public <T> List<T> list(Class<T> tClass, Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeList(tClass, JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public String value(Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeValue(JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public <T> T value(Class<T> tClass, Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeValue(tClass, JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public List<String> values(Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeValues(JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public <T> List<T> values(Class<T> tClass, Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeValues(tClass, JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public List<TableMap> page(Columns columns, TableJoinOn tableJoinOn, TableMap searchMap, NeoPage neoPage) {
        return neo.exePage(JoinSqlBuilder.build(columns, tableJoinOn, searchMap), neoPage, JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public <T> List<T> page(Class<T> tClass, Columns columns, TableJoinOn tableJoinOn, TableMap searchMap, NeoPage neoPage) {
        return neo.exePage(tClass, JoinSqlBuilder.build(columns, tableJoinOn, searchMap), neoPage, JoinSqlBuilder.buildValueList(searchMap).toArray());
    }

    @Override
    public Integer count(Columns columns, TableJoinOn tableJoinOn, TableMap searchMap) {
        return neo.exeCount(JoinSqlBuilder.build(columns, tableJoinOn, searchMap), JoinSqlBuilder.buildValueList(searchMap).toArray());
    }
}
